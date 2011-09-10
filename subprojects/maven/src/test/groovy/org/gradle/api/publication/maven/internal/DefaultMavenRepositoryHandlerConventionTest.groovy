/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.publication.maven.internal

import org.gradle.api.artifacts.maven.GroovyMavenDeployer
import org.gradle.api.artifacts.maven.MavenResolver
import org.gradle.api.internal.artifacts.ResolverFactory
import org.gradle.api.internal.artifacts.dsl.DefaultRepositoryHandler
import spock.lang.Specification
import org.gradle.api.internal.file.FileResolver
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.maven.Conf2ScopeMappingContainer

class DefaultMavenRepositoryHandlerConventionTest extends Specification {
    final DefaultRepositoryHandler container = Mock()
    final FileResolver fileResolver = Mock()
    final ConfigurationContainer configurationContainer = Mock()
    final Conf2ScopeMappingContainer conf2ScopeMappingContainer = Mock()
    final ResolverFactory factory = Mock()
    final DefaultMavenRepositoryHandlerConvention convention = new DefaultMavenRepositoryHandlerConvention(container)

    def setup() {
        _ * container.resolverFactory >> factory
        _ * container.fileResolver >> fileResolver
        _ * container.configurationContainer >> configurationContainer
        _ * container.mavenScopeMappings >> conf2ScopeMappingContainer
    }

    public void mavenDeployerWithoutName() {
        GroovyMavenDeployer deployer = Mock()

        when:
        def result = convention.mavenDeployer()

        then:
        result == deployer
        1 * factory.createMavenDeployer(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> deployer
        1 * container.addRepository(deployer, "mavenDeployer") >> deployer
    }

    public void mavenDeployerWithArgs() {
        GroovyMavenDeployer deployer = Mock()

        when:
        def result = convention.mavenDeployer(name: 'someName')

        then:
        result == deployer
        1 * factory.createMavenDeployer(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> deployer
        1 * container.addRepository(deployer, [name: 'someName'], "mavenDeployer") >> deployer
    }

    public void mavenDeployerWithArgsAndClosure() {
        GroovyMavenDeployer deployer = Mock()
        def cl = {
            name = 'other'
        }

        when:
        def result = convention.mavenDeployer(name: 'someName', cl)

        then:
        result == deployer
        1 * factory.createMavenDeployer(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> deployer
        1 * container.addRepository(deployer, [name: 'someName'], cl, "mavenDeployer") >> deployer
    }

    public void mavenDeployerWithClosure() {
        GroovyMavenDeployer deployer = Mock()
        def cl = {
            name = 'other'
        }

        when:
        def result = convention.mavenDeployer(cl)

        then:
        result == deployer
        1 * factory.createMavenDeployer(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> deployer
        1 * container.addRepository(deployer, cl, "mavenDeployer") >> deployer
    }

    public void mavenInstallerWithoutName() {
        MavenResolver installer = Mock()

        when:
        def result = convention.mavenInstaller()

        then:
        result == installer
        1 * factory.createMavenInstaller(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> installer
        1 * container.addRepository(installer, "mavenInstaller") >> installer
    }

    public void mavenInstallerWithArgs() {
        MavenResolver installer = Mock()

        when:
        def result = convention.mavenInstaller(name: 'name')

        then:
        result == installer
        1 * factory.createMavenInstaller(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> installer
        1 * container.addRepository(installer, [name: 'name'], "mavenInstaller") >> installer
    }

    public void mavenInstallerWithNameAndClosure() {
        MavenResolver installer = Mock()
        def cl = { name = 'other' }

        when:
        def result = convention.mavenInstaller(name: 'name', cl)

        then:
        result == installer
        1 * factory.createMavenInstaller(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> installer
        1 * container.addRepository(installer, [name: 'name'], cl, "mavenInstaller") >> installer
    }

    public void mavenInstallerWithClosure() {
        MavenResolver installer = Mock()
        def cl = { name = 'other' }

        when:
        def result = convention.mavenInstaller(cl)

        then:
        result == installer
        1 * factory.createMavenInstaller(container, configurationContainer, conf2ScopeMappingContainer, fileResolver) >> installer
        1 * container.addRepository(installer, cl, "mavenInstaller") >> installer
    }

}

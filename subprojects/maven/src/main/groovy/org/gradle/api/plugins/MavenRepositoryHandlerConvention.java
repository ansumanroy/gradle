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
package org.gradle.api.plugins;

import groovy.lang.Closure;
import org.gradle.api.artifacts.maven.GroovyMavenDeployer;
import org.gradle.api.artifacts.maven.MavenResolver;

import java.util.Map;

/**
 * Allows maven repositories for publishing artifacts to be defined. The maven plugin mixes-in this interface to the {@link org.gradle.api.artifacts.dsl.RepositoryHandler} associated with each
 * task of type {@link org.gradle.api.tasks.Upload}.
 */
public interface MavenRepositoryHandlerConvention {
    GroovyMavenDeployer mavenDeployer();

    GroovyMavenDeployer mavenDeployer(Closure configureClosure);

    /**
     * Adds a repository for publishing to a Maven repository. This repository can not be used for reading from a Maven
     * repository.
     *
     * The following parameter are accepted as keys for the map:
     *
     * <table summary="Shows property keys and associated values">
     * <tr><th>Key</th>
     *     <th>Description of Associated Value</th></tr>
     * <tr><td><code>name</code></td>
     *     <td><em>(optional)</em> The name of the repository. The default is <em>mavenDeployer-{SOME_ID}</em>.
     * The name is used in the console output,
     * to point to information related to a particular repository. A name must be unique amongst a repository group.
     * </td></tr>
     * </table>
     *
     * @param args The argument to create the repository
     * @return The added repository
     * @see #mavenDeployer(java.util.Map, groovy.lang.Closure)
     */
    GroovyMavenDeployer mavenDeployer(Map<String, ?> args);

    /**
     * Behaves the same way as {@link #mavenDeployer(java.util.Map)}. Additionally a closure can be passed to
     * further configure the added repository.
     *
     * @param args The argument to create the repository
     * @param configureClosure
     * @return The added repository
     */
    GroovyMavenDeployer mavenDeployer(Map<String, ?> args, Closure configureClosure);

    MavenResolver mavenInstaller();

    MavenResolver mavenInstaller(Closure configureClosure);

    /**
     * Adds a repository for installing to a local Maven cache. This repository can not be used for reading.
     *
     * The following parameter are accepted as keys for the map:
     *
     * <table summary="Shows property keys and associated values">
     * <tr><th>Key</th>
     *     <th>Description of Associated Value</th></tr>
     * <tr><td><code>name</code></td>
     *     <td><em>(optional)</em> The name of the repository. The default is <em>mavenInstaller-{SOME_ID}</em>.
     * The name is used in the console output,
     * to point to information related to a particular repository. A name must be unique amongst a repository group.
     * </td></tr>
     * </table>
     *
     * @param args The argument to create the repository
     * @return The added repository
     * @see #mavenInstaller(java.util.Map, groovy.lang.Closure) (java.util.Map, groovy.lang.Closure)
     */
    MavenResolver mavenInstaller(Map<String, ?> args);

    /**
     * Behaves the same way as {@link #mavenInstaller(java.util.Map)}. Additionally a closure can be passed to further configure the added repository.
     *
     * @param args The argument to create the repository
     * @return The added repository
     */
    MavenResolver mavenInstaller(Map<String, ?> args, Closure configureClosure);
}

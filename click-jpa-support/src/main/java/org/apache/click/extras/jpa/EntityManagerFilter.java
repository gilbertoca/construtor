/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.click.extras.jpa;

import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * Provides a EntityManager filter to support the EntityManagerContext class,
 * closing EntityManager at the end of each request. <p/> To use
 * {@link EntityManagerContext} configure the EntityManagerFilter in you web
 * application's <tt>/WEB-INF/web.xml</tt> file.
 * 
 * <pre class="codeConfig">
 *  &lt;web-app&gt;
 *    &lt;filter&gt;
 *      &lt;filter-name&gt;&lt;font color=&quot;blue&quot;&gt;entityManager-filter&lt;/font&gt;&lt;/filter-name&gt;
 *      &lt;filter-class&gt;&lt;font color=&quot;red&quot;&gt;net.sf.click.extras.jpa.EntityManagerFilter&lt;/font&gt;&lt;/filter-class&gt;
 *    &lt;/filter&gt;
 * 
 *    &lt;filter-mapping&gt;
 *      &lt;filter-name&gt;&lt;font color=&quot;blue&quot;&gt;entityManager-filter&lt;/font&gt;&lt;/filter-name&gt;
 *      &lt;servlet-name&gt;&lt;font color=&quot;green&quot;&gt;click-servlet&lt;/font&gt;&lt;/servlet-name&gt;
 *    &lt;/filter-mapping&gt;
 * 
 *    &lt;servlet&gt;
 *      &lt;servlet-name&gt;&lt;font color=&quot;green&quot;&gt;click-servlet&lt;/font&gt;&lt;/servlet-name&gt;
 *    ..
 *  &lt;/web-app&gt;
 * </pre>
 * 
 * <p/> The EntityManagerFilter <tt>init()</tt> method loads the
 * EntityManagerContext class which in turn initializes the JPA runtime.
 * 
 * @see EntityManagerContext
 * @see JpaForm
 */
public class EntityManagerFilter implements Filter {

    /**
     * Initialize the JPA Configuration and EntityManagerFactory.
     *
     * @see Filter#init(FilterConfig)
     *
     * @param filterConfig
     *            the filter configuration
     * @throws ServletException
     *             if an initialization error occurs
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // Load the EntityManagerContext class initializing the
        // EntityManagerFactory
        try {
            EntityManagerContext.class.getName();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * Close any user defined sessions if present.
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     *
     * @param request
     *            the servlet request
     * @param response
     *            the servlet response
     * @param chain
     *            the filter chain
     * @throws IOException
     *             if an I/O error occurs
     * @throws ServletException
     *             if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        EntityTransaction transaction = EntityManagerContext.getEntityManager().getTransaction();
        transaction.begin();
        chain.doFilter(request, response);

        if (EntityManagerContext.hasEntityManager()
                && EntityManagerContext.getEntityManager().isOpen()) {
            if (EntityManagerContext.getEntityManager().getTransaction().isActive()) {
                transaction.commit();
            }
            EntityManagerContext.close();
        }
    }
}

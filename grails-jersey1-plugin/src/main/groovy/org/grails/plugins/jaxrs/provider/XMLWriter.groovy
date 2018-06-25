/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.plugins.jaxrs.provider

import grails.converters.XML
import org.grails.web.converters.configuration.ConvertersConfigurationHolder

import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.Provider

/**
 * Provider for Grails' {@link XML} converter class.
 *
 * Message body writer that supports {@link XML} response entities. For example,
 * to create an XML representation from a list of Grails domain objects:
 *
 * <pre>
 * &#064;Path('/notes')
 * &#064;Produces('text/xml') // or 'application/xml'
 * class NotesResource {*
 *      &#064;GET
 *      XML findNotes() {*          Note.findAll() as XML
 *}*
 *}* </pre>
 *
 * Alternatively, one could write
 *
 * <pre>
 * &#064;Path('/notes')
 * &#064;Produces('text/xml') // or 'application/xml'
 * class NotesResource {*
 *      &#064;GET
 *      Response findNotes() {*          Response.ok(Note.findAll() as XML).build()
 *}*
 *}* </pre>
 *
 * @author Martin Krasser
 */
@Provider
@Produces(["text/xml", "application/xml"])
class XMLWriter extends MessageBodyWriterSupport<XML> {

    static final String DEFAULT_CHARSET = "UTF-8"

    /**
     * Renders the <code>xml</code> object to the response's
     * <code>entityStream</code> using the encoding set by the Grails
     * application.
     *
     * @param xml
     *            XML object.
     * @param httpHeaders
     *            HTTP headers
     * @param entityStream
     *            XML response entity stream.
     */
    @Override
    protected void writeTo(XML xml, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
        throws IOException, WebApplicationException {
        String charset = ConvertersConfigurationHolder.getConverterConfiguration(XML.class).getEncoding()
        Writer writer = new OutputStreamWriter(entityStream, charset == null ? DEFAULT_CHARSET : charset)
        xml.render(writer)
    }
}

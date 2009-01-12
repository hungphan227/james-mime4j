/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.mime4j.field;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class FieldsTest extends TestCase {

    public void testContentTypeString() throws Exception {
        ContentTypeField field = Fields.contentType("multipart/mixed; "
                + "boundary=\"-=Part.0.37877968dd4f6595.11eccf0271c"
                + ".2dce5678cbc933d5=-\"");
        assertTrue(field.isValidField());

        String expectedRaw = "Content-Type: multipart/mixed;\r\n "
                + "boundary=\"-=Part.0.37877968dd4f6595.11eccf0271c"
                + ".2dce5678cbc933d5=-\"";
        assertEquals(expectedRaw, field.getRaw());
    }

    public void testContentTypeStringParameters() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("boundary",
                "-=Part.0.37877968dd4f6595.11eccf0271c.2dce5678cbc933d5=-");
        ContentTypeField field = Fields.contentType("multipart/mixed",
                parameters);
        assertTrue(field.isValidField());

        String expectedRaw = "Content-Type: multipart/mixed;\r\n "
                + "boundary=\"-=Part.0.37877968dd4f6595.11eccf0271c"
                + ".2dce5678cbc933d5=-\"";
        assertEquals(expectedRaw, field.getRaw());
    }

    public void testContentTypeStringNullParameters() throws Exception {
        ContentTypeField field = Fields.contentType("text/plain", null);
        assertTrue(field.isValidField());

        String expectedRaw = "Content-Type: text/plain";
        assertEquals(expectedRaw, field.getRaw());
    }

    public void testInvalidContentType() throws Exception {
        ContentTypeField field = Fields.contentType("multipart/mixed; "
                + "boundary=-=Part.0.37877968dd4f6595.11eccf0271c"
                + ".2dce5678cbc933d5=-");
        assertFalse(field.isValidField());

        assertEquals("multipart/mixed", field.getMimeType());
    }

    public void testContentTransferEncoding() throws Exception {
        ContentTransferEncodingField field = Fields
                .contentTransferEncoding("base64");
        assertTrue(field.isValidField());

        assertEquals("Content-Transfer-Encoding: base64", field.getRaw());
    }

}
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
package org.kie.kogito.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestTest {

    private Request request;

    @BeforeEach
    public void setUp() {
        request = new Request();
    }

    @Test
    public void testMapJson() throws Exception {
        String json =
                "{\"Components\":[{\"PartName\":\"Composant Hugo\",\"Number\":\"45117685\",\"Revision\":\"--\",\"LogisticStatus\":\"Off\"},{\"Components\":[{\"Components\":[{\"Components\":[{\"PartName\":\"Composant Donatien\",\"Number\":\"62761431\",\"Revision\":\"-B\",\"LogisticStatus\":\"Off\"}],\"PartName\":\"Composant Hervé\",\"Number\":\"62952916\",\"Revision\":\"-F\",\"LogisticStatus\":\"On\"}],\"PartName\":\"Composant Cindy\",\"Number\":\"62952908\",\"Revision\":\"-D\",\"LogisticStatus\":\"On\"}],\"PartName\":\"Composant Lila\",\"Number\":\"62952907\",\"Revision\":\"--\",\"LogisticStatus\":\"Off\"},{\"PartName\":\"Composant Audrey\",\"Number\":\"62764376\",\"Revision\":\"-B\",\"LogisticStatus\":\"On\"},{\"PartName\":\"Composant Soanjara\",\"Number\":\"96149989\",\"Revision\":\"-F\",\"LogisticStatus\":\"Off\"},{\"Components\":[{\"Components\":[{\"PartName\":\"Composant Lucie\",\"Number\":\"62426404\",\"Revision\":\"-D\",\"LogisticStatus\":\"On\"}],\"PartName\":\"Composant Sabrina\",\"Number\":\"62426472\",\"Revision\":\"-F\",\"LogisticStatus\":\"Off\"},{\"Components\":[{\"PartName\":\"Composant Emilie\",\"Number\":\"62110232\",\"Revision\":\"-H\",\"LogisticStatus\":\"On\"}],\"PartName\":\"Composant Elmut\",\"Number\":\"62426466\",\"Revision\":\"-F\",\"LogisticStatus\":\"Off\"},{\"Components\":[{\"PartName\":\"Composant Friedrich\",\"Number\":\"62109451\",\"Revision\":\"-F\",\"LogisticStatus\":\"Off\"},{\"PartName\":\"Composant Sullivan\",\"Number\":\"92110245\",\"Revision\":\"-G\",\"LogisticStatus\":\"On\"}],\"PartName\":\"Composant Yann\",\"Number\":\"82426503\",\"Revision\":\"-D\",\"LogisticStatus\":\"Off\"},{\"PartName\":\"Composant Robin \",\"Number\":\"62110040\",\"Revision\":\"-A\",\"LogisticStatus\":\"On\"},{\"PartName\":\"Composant Patrick\",\"Number\":\"62426407\",\"Revision\":\"-F\",\"LogisticStatus\":\"Off\"}],\"PartName\":\"Composant François\",\"Number\":\"42952905\",\"Revision\":\"-P\",\"LogisticStatus\":\"Off\"},{\"PartName\":\"Composant Anatole\",\"Number\":\"53097104\",\"Revision\":\"--\",\"LogisticStatus\":\"On\"},{\"PartName\":\"Composant Natacha\",\"Number\":\"62538521\",\"Revision\":\"-A\",\"LogisticStatus\":\"On\"},{\"PartName\":\"Composant Frederic\",\"Number\":\"62538519\",\"Revision\":\"-A\",\"LogisticStatus\":\"Off\"}],\"PartName\":\"Système\",\"Number\":\"62952894\",\"Revision\":\"AD\",\"LogisticStatus\":\"Off\"}";
        request.MapJson(json);
        List<Map<String, Object>> components = request.getComponents();
        Assertions.assertEquals(1, components.size());

        // Test specific components and their attributes
        Map<String, Object> systemComponent = components.get(0);
        Assertions.assertEquals("Système", systemComponent.get("PartName"));
        Assertions.assertEquals("62952894", systemComponent.get("Number"));
        Assertions.assertEquals("AD", systemComponent.get("Revision"));
        Assertions.assertEquals("Off", systemComponent.get("LogisticStatus"));
    }

    @Test
    public void testSetRulesWithApplicableOn() {
        String ruleName = "Rule1";
        String indication = "Indication1";
        List<String> results = Arrays.asList("Result1", "Result2");
        List<String> resultsNOK = Arrays.asList("ResultNOK1", "ResultNOK2");
        List<String> applicableOn = Arrays.asList("ApplicableOn1", "ApplicableOn2");

        request.setRules(ruleName, indication, applicableOn, results, resultsNOK);

        List<Map<String, Object>> rules = request.getRules();
        Assertions.assertEquals(1, rules.size());
        Map<String, Object> ruleInfo = rules.get(0);
        Assertions.assertEquals(ruleName, ruleInfo.get("ruleName"));
        Assertions.assertEquals(indication, ruleInfo.get("Indication"));
        Assertions.assertEquals(applicableOn, ruleInfo.get("applicableOn"));
        Assertions.assertEquals(results, ruleInfo.get("results"));
        Assertions.assertEquals(resultsNOK, ruleInfo.get("resultsNOK"));
    }

    @Test
    public void testSetRulesWithoutApplicableOn() {
        String ruleName = "Rule1";
        String indication = "Indication1";
        List<String> results = Arrays.asList("Result1", "Result2");
        List<String> resultsNOK = Arrays.asList("ResultNOK1", "ResultNOK2");

        request.setRules(ruleName, indication, results, resultsNOK);

        List<Map<String, Object>> rules = request.getRules();
        Assertions.assertEquals(1, rules.size());
        Map<String, Object> ruleInfo = rules.get(0);
        Assertions.assertEquals(ruleName, ruleInfo.get("ruleName"));
        Assertions.assertEquals(indication, ruleInfo.get("Indication"));
        Assertions.assertNull(ruleInfo.get("applicableOn")); // ApplicableOn est null dans cette méthode
        Assertions.assertEquals(results, ruleInfo.get("results"));
        Assertions.assertEquals(resultsNOK, ruleInfo.get("resultsNOK"));
    }

}

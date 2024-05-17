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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {

    private String json;
    private List<Map<String, Object>> components;
    private boolean received;
    private List<Map<String, Object>> rules;

    public void setRules(String ruleName, String indication, List<String> applicableOn, List<String> results, List<String> resultsNOK) {
        if (this.rules == null) {
            this.rules = new ArrayList<>();
        }
        Map<String, Object> ruleInfo = new HashMap<>();
        ruleInfo.put("resultsNOK", resultsNOK);
        ruleInfo.put("results", results);
        ruleInfo.put("applicableOn", applicableOn);
        ruleInfo.put("Indication", indication);
        ruleInfo.put("ruleName", ruleName);
        this.rules.add(ruleInfo);
    }

    public void setRules(String ruleName, String indication, List<String> results, List<String> resultsNOK) {
        if (this.rules == null) {
            this.rules = new ArrayList<>();
        }
        Map<String, Object> ruleInfo = new HashMap<>();
        ruleInfo.put("results", results);
        ruleInfo.put("resultsNOK", resultsNOK);
        ruleInfo.put("Indication", indication);
        ruleInfo.put("ruleName", ruleName);
        this.rules.add(ruleInfo);
    }

    public List<Map<String, Object>> getRules() {
        return rules;
    }

    public Request() {
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public boolean getReceived() {
        return received;
    }

    public List<Map<String, Object>> getComponents() {
        return components;
    }

    public void setComponents(List<Map<String, Object>> components) {
        this.components = components;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    // Méthode pour mapper un JSON en liste de composants
    public void MapJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        // Initialiser la liste pour contenir les composants
        List<Map<String, Object>> components = new ArrayList<>();

        // Traiter le nœud racine pour identifier le composant principal "Système"
        Map<String, Object> systemComponent = new HashMap<>();
        processComponent(rootNode, systemComponent);

        // Vérifier si le composant principal a un champ "components" au premier niveau d'imbrication
        if (systemComponent.containsKey("components")) {
            // S'il en a un, déplacer les composants d'un niveau vers le haut et les ajouter au composant principal
            List<Map<String, Object>> systemComponents = (List<Map<String, Object>>) systemComponent.get("components");
            systemComponent.remove("components");
            components.addAll(systemComponents);
        }

        // Si le composant système n'est pas vide, l'ajouter à la liste des composants
        if (!systemComponent.isEmpty()) {
            components.add(systemComponent);
        }

        // S'il y a des composants, définir la liste de composants dans l'objet Request
        if (!components.isEmpty()) {
            this.components = components;
        }
    }

    // Méthode récursive pour traiter un nœud JSON et le mapper en composant
    private void processComponent(JsonNode node, Map<String, Object> componentMap) {
        // Extraire les propriétés du nœud actuel
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isArray()) {
                // Si la valeur du champ est un tableau, traiter récursivement chaque élément
                List<Map<String, Object>> nestedComponents = new ArrayList<>();
                for (JsonNode arrayItem : fieldValue) {
                    Map<String, Object> nestedComponent = new HashMap<>();
                    processComponent(arrayItem, nestedComponent);
                    nestedComponents.add(nestedComponent);
                }
                // Ajouter le tableau de composants sous la carte de composant principal
                componentMap.put(fieldName, nestedComponents);
            } else if (fieldValue.isObject()) {
                // Si la valeur du champ est un objet, le traiter récursivement
                Map<String, Object> nestedComponent = new HashMap<>();
                processComponent(fieldValue, nestedComponent);
                // Ajouter la carte de composant imbriqué sous la carte de composant principal
                componentMap.put(fieldName, nestedComponent);
            } else {
                // Ajouter les autres champs directement à la carte de composant
                componentMap.put(fieldName, fieldValue.asText());
            }
        }
    }
}

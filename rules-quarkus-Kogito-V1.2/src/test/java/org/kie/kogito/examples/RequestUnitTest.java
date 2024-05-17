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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestUnitTest {

    @Test
    public void testReturnORID() {
        // Créer un exemple de composant
        Map<String, Object> component = new HashMap<>();
        component.put("LogisticStatus", "Off");
        component.put("Revision", "--");
        component.put("Number", "1");

        // Appeler la fonction à tester
        List<String> result = returnORID(component, Arrays.asList("LogisticStatus", "Revision"),
                Arrays.asList(Arrays.asList("Off"), Arrays.asList("--")));

        // Vérifier le résultat
        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    public void testCheckORFor() {
        // Créer un exemple de composant
        Map<String, Object> component = new HashMap<>();
        component.put("LogisticStatus", "Off");
        component.put("Revision", "--");

        // Appeler la fonction à tester
        boolean result = checkORFor(component, Arrays.asList("LogisticStatus", "Revision"),
                Arrays.asList(Arrays.asList("Off"), Arrays.asList("--")));

        // Vérifier le résultat
        assertTrue(result);
    }

    @Test
    public void testReturnANDID() {
        // Créer un exemple de composant
        Map<String, Object> component = new HashMap<>();
        component.put("LogisticStatus", "Off");
        component.put("Revision", "--");
        component.put("Number", "1");

        // Appeler la fonction à tester
        List<String> result = returnANDID(component, Arrays.asList("LogisticStatus", "Revision"),
                Arrays.asList(Arrays.asList("Off"), Arrays.asList("--")));

        // Vérifier le résultat
        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    public void testCheckANDFor() {
        // Créer un exemple de composant
        Map<String, Object> component = new HashMap<>();
        component.put("LogisticStatus", "Off");
        component.put("Revision", "--");

        // Appeler la fonction à tester
        boolean result = checkANDFor(component, Arrays.asList("LogisticStatus", "Revision"),
                Arrays.asList(Arrays.asList("Off"), Arrays.asList("--")));

        // Vérifier le résultat
        assertTrue(result);
    }

    private List<String> returnORID(Map component, List<String> champs, List<List<String>> valeurs) {
        List<String> maliste = new ArrayList<String>();

        // Vérifier si le composant correspond à au moins l'un des critères
        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            // Vérifier si le composant correspond à la valeur du champ actuel
            if (valeur.contains(component.get(champ))) {
                maliste.add((String) component.get("Number"));
                // Pas besoin de vérifier les autres critères si une correspondance est trouvée
                break;
            }
        }

        // Parcourir les sous-composants
        for (Object entryObj : component.entrySet()) {
            if (entryObj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) entryObj;
                if (entry.getValue() instanceof Map) {
                    maliste.addAll(returnORID((Map) entry.getValue(), champs, valeurs));
                } else if (entry.getValue() instanceof List) {
                    for (Object item : (List) entry.getValue()) {
                        if (item instanceof Map) {
                            maliste.addAll(returnORID((Map) item, champs, valeurs));
                        }
                    }
                }
            }
        }

        return maliste;
    }

    private List<String> returnORIDNOK(Map component, List<String> champs, List<List<String>> valeurs) {
        List<String> maliste = new ArrayList<String>();
        boolean correspondanceTrouvee = false;

        // Vérifier si le composant correspond à au moins l'un des critères
        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            // Vérifier si le composant correspond à la valeur du champ actuel
            if (valeur.contains(component.get(champ))) {
                correspondanceTrouvee = true;
                break;
            }
        }

        // Si aucune correspondance n'a été trouvée, ajouter le composant à la liste
        if (!correspondanceTrouvee) {
            maliste.add((String) component.get("Number"));
        }

        // Parcourir les sous-composants
        for (Object entryObj : component.entrySet()) {
            if (entryObj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) entryObj;
                if (entry.getValue() instanceof Map) {
                    maliste.addAll(returnORIDNOK((Map) entry.getValue(), champs, valeurs));
                } else if (entry.getValue() instanceof List) {
                    for (Object item : (List) entry.getValue()) {
                        if (item instanceof Map) {
                            maliste.addAll(returnORIDNOK((Map) item, champs, valeurs));
                        }
                    }
                }
            }
        }
        return maliste;
    }

    private List<String> returnANDID(Map component, List<String> champs, List<List<String>> valeurs) {
        List<String> maliste = new ArrayList<String>();
        boolean correspondanceTrouvee = true; // Indicateur pour suivre si le composant correspond à tous les critères

        // Vérifier si le composant correspond à toutes les conditions
        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            // Vérifier si le composant correspond à la valeur du champ actuel
            if (!valeur.contains(component.get(champ))) {
                correspondanceTrouvee = false;
                break; // Sortir de la boucle dès qu'une correspondance n'est pas trouvée
            }
        }

        // Si le composant correspond à toutes les conditions, l'ajouter à la liste
        if (correspondanceTrouvee) {
            maliste.add((String) component.get("Number"));
        }

        // Parcourir les sous-composants
        for (Object entryObj : component.entrySet()) {
            if (entryObj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) entryObj;
                if (entry.getValue() instanceof Map) {
                    maliste.addAll(returnANDID((Map) entry.getValue(), champs, valeurs));
                } else if (entry.getValue() instanceof List) {
                    for (Object item : (List) entry.getValue()) {
                        if (item instanceof Map) {
                            maliste.addAll(returnANDID((Map) item, champs, valeurs));
                        }
                    }
                }
            }
        }

        return maliste;
    }

    private List<String> returnANDIDNOK(Map component, List<String> champs, List<List<String>> valeurs) {
        List<String> maliste = new ArrayList<String>();
        boolean correspondanceTrouvee = true; // Indicateur pour suivre si le composant correspond à tous les critères

        // Vérifier si le composant correspond à toutes les conditions
        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            // Vérifier si le composant ne correspond pas à la valeur du champ actuel
            if (!valeur.contains(component.get(champ))) {
                correspondanceTrouvee = false;
                break; // Sortir de la boucle dès qu'une correspondance n'est pas trouvée
            }
        }

        // Si le composant ne correspond pas à toutes les conditions, l'ajouter à la liste
        if (!correspondanceTrouvee) {
            maliste.add((String) component.get("Number"));
        }

        // Parcourir les sous-composants
        for (Object entryObj : component.entrySet()) {
            if (entryObj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) entryObj;
                if (entry.getValue() instanceof Map) {
                    maliste.addAll(returnANDIDNOK((Map) entry.getValue(), champs, valeurs));
                } else if (entry.getValue() instanceof List) {
                    for (Object item : (List) entry.getValue()) {
                        if (item instanceof Map) {
                            maliste.addAll(returnANDIDNOK((Map) item, champs, valeurs));
                        }
                    }
                }
            }
        }

        return maliste;
    }

    private List<String> returnORParentChild(Map<String, Object> component, List<String> champsPere, List<String> champsFils, List<List<String>> valeursPere, List<List<String>> valeursFils) {
        List<String> pairs = new ArrayList<>();

        // Vérifier si le composant actuel correspond aux critères de recherche
        boolean correspondance = false;

        // Vérifier chaque critère de recherche pour les champs pères
        for (int i = 0; i < champsPere.size(); i++) {
            String champPere = champsPere.get(i);
            List<String> valeurs = valeursPere.get(i);
            if (valeurs.contains(component.get(champPere))) {
                correspondance = true;
                break; // Sortir de la boucle dès qu'une correspondance est trouvée
            }
        }

        // Si aucune correspondance n'est trouvée pour les champs pères, retourner une liste vide
        if (!correspondance) {
            return pairs;
        }

        // Vérifier chaque critère de recherche pour les champs fils
        for (int i = 0; i < champsFils.size(); i++) {
            String champFils = champsFils.get(i);
            List<String> valeurs = valeursFils.get(i);
            System.out.println("[FUNC][returnORParentChild] L'élément " + component.get("PartName") + " répond aux critères père");
            // Parcourir les enfants du composant actuel
            List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
            if (children != null) {
                for (Map<String, Object> child : children) {
                    if (valeurs.contains(child.get(champFils))) {
                        String pair = "P: " + component.get("PartName") + ", F: " + child.get("PartName");
                        pairs.add(pair);
                        System.out.println("[FUNC][returnORParentChild] Fils trouvé pour le parent, paire : " + pair);
                    } else {
                        System.out.println("[FUNC][returnORParentChild] Le fils de " + component.get("PartName") + " ne répond pas aux critères : " + child.get("PartName"));
                    }

                    // Appel récursif pour les enfants du composant actuel
                    pairs.addAll(returnORParentChild(child, champsPere, champsFils, valeursPere, valeursFils));
                }
            } else {
                System.out.println("[FUNC][returnORParentChild] Liste des enfants vide pour " + component.get("PartName"));
            }
        }

        return pairs;
    }

    private List<String> returnORParentChildNOK(Map<String, Object> component, List<String> champsPere, List<String> champsFils, List<List<String>> valeursPere, List<List<String>> valeursFils) {
        List<String> pairs = new ArrayList<>();

        // Vérifier si le composant actuel ne correspond pas aux critères de recherche pour les champs pères
        boolean correspondanceTrouvee = false;
        for (int i = 0; i < champsPere.size(); i++) {
            String champPere = champsPere.get(i);
            List<String> valeurs = valeursPere.get(i);
            if (valeurs.contains(component.get(champPere))) {
                correspondanceTrouvee = true;
                break; // Sortir de la boucle dès qu'une correspondance est trouvée
            }
        }

        // Vérifier chaque critère de recherche pour les champs fils
        boolean childFound = false; // Indique si au moins un enfant satisfait les critères
        for (int i = 0; i < champsFils.size(); i++) {
            String champFils = champsFils.get(i);
            System.out.println("[FUNC][returnORParentChildNOK] L'élément " + component.get("PartName") + " ne répond pas aux critères père (on l'ajoute)");
            List<String> valeurs = valeursFils.get(i);

            // Parcourir les enfants du composant actuel
            List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
            if (children != null) {
                for (Map<String, Object> child : children) {
                    if (!valeurs.contains(child.get(champFils))) {
                        // Ajouter la paire parent/enfant à la liste
                        String pair = "P: " + component.get("PartName") + ", F: " + child.get("PartName");
                        pairs.add(pair);
                        System.out.println("[FUNC][returnORParentChildNOK] Enfant ne correspondant pas trouvé paire : " + pair);
                        childFound = true; // Marquer qu'au moins un enfant a été trouvé
                    } else {
                        System.out.println("[FUNC][returnORParentChildNOK] Le fils de " + component.get("PartName") + " répond aux critères : " + child.get("PartName") + " (on ne l'ajoute pas)");
                    }

                    // Appel récursif pour les enfants du composant actuel
                    pairs.addAll(returnORParentChildNOK(child, champsPere, champsFils, valeursPere, valeursFils));
                }
            } else {
                // Si la liste des enfants est vide, afficher un message
                System.out.println("[FUNC][returnORParentChildNOK] Liste des enfants vide pour " + component.get("PartName"));
            }
        }

        // Si aucun enfant ne satisfait les critères et aucun critère père n'est satisfait, ne pas ajouter le composant seul à la liste
        if (!correspondanceTrouvee && !childFound) {
            System.out.println("[FUNC][returnORParentChildNOK] Aucun enfant ne correspond et aucun critère père n'est satisfait pour le composant : " + component.get("PartName"));
        }

        return pairs; // Retourner la liste des paires parent/enfant
    }

    private List<String> returnANDParentChild(Map<String, Object> component, List<String> champsPere, List<String> champsFils, List<List<String>> valeursPere, List<List<String>> valeursFils) {
        List<String> pairs = new ArrayList<>();

        // Vérifier si le composant actuel correspond à tous les critères de recherche pour les champs pères
        boolean correspondancePere = true;
        for (int i = 0; i < champsPere.size(); i++) {
            String champPere = champsPere.get(i);
            List<String> valeurs = valeursPere.get(i);
            if (!valeurs.contains(component.get(champPere))) {
                correspondancePere = false;
                break; // Sortir de la boucle dès qu'une non-correspondance est trouvée
            }
        }

        // Si le composant actuel correspond à tous les critères de recherche pour les champs pères
        if (correspondancePere) {
            // Vérifier chaque critère de recherche pour les champs fils
            boolean correspondanceFils = true;
            for (int i = 0; i < champsFils.size(); i++) {
                String champFils = champsFils.get(i);
                List<String> valeurs = valeursFils.get(i);

                // Parcourir les enfants du composant actuel
                List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
                if (children != null) {
                    boolean filsCorrespondantTrouve = false;
                    for (Map<String, Object> child : children) {
                        if (valeurs.contains(child.get(champFils))) {
                            filsCorrespondantTrouve = true;
                            break; // Sortir de la boucle dès qu'une correspondance est trouvée
                        }
                    }
                    // Si aucun fils ne correspond au critère, la correspondance globale est fausse
                    if (!filsCorrespondantTrouve) {
                        correspondanceFils = false;
                        break;
                    }
                } else {
                    System.out.println("Liste des enfants vide pour " + component.get("PartName"));
                    correspondanceFils = false;
                }
            }

            // Si le composant actuel correspond à tous les critères pour les champs pères et fils, ajouter le couple Parent-Enfant à la liste
            if (correspondanceFils) {
                StringBuilder pair = new StringBuilder();
                pair.append("P: ").append(component.get("PartName")).append(", ");

                // Ajouter les enfants correspondants à la paire
                List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
                if (children != null && !children.isEmpty()) {
                    pair.append("F: ");
                    for (Map<String, Object> child : children) {
                        pair.append(child.get("PartName")).append(", ");
                    }
                    // Supprimer la virgule finale et ajouter la paire à la liste
                    pair.delete(pair.length() - 2, pair.length());
                    pairs.add(pair.toString());
                } else {
                    System.out.println("Liste des enfants vide pour " + component.get("PartName"));
                }
            }
        }

        // Appel récursif pour les enfants du composant actuel
        List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
        if (children != null) {
            for (Map<String, Object> child : children) {
                pairs.addAll(returnANDParentChild(child, champsPere, champsFils, valeursPere, valeursFils));
            }
        }

        return pairs;
    }

    private List<String> returnANDParentChildNOK(Map<String, Object> component, List<String> champsPere, List<String> champsFils, List<List<String>> valeursPere, List<List<String>> valeursFils) {
        List<String> pairs = new ArrayList<>();

        // Vérifier si le composant actuel correspond à tous les critères de recherche pour les champs pères
        boolean correspondancePere = true;
        for (int i = 0; i < champsPere.size(); i++) {
            String champPere = champsPere.get(i);
            List<String> valeurs = valeursPere.get(i);
            if (!valeurs.contains(component.get(champPere))) {
                correspondancePere = false;
                break; // Sortir de la boucle dès qu'une non-correspondance est trouvée
            }
        }

        // Si le composant actuel ne correspond pas à tous les critères de recherche pour les champs pères, ajouter le composant à la liste
        if (!correspondancePere) {
            // Ajouter les enfants correspondants à la paire
            List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
            if (children != null && !children.isEmpty()) {
                for (Map<String, Object> child : children) {
                    StringBuilder pair = new StringBuilder("P: ");
                    pair.append(component.get("PartName")).append(", ");
                    pair.append("F: ").append(child.get("PartName"));
                    pairs.add(pair.toString());
                }
            } else {
                System.out.println("Liste des enfants vide pour " + component.get("PartName"));
            }
        }

        // Appel récursif pour les enfants du composant actuel
        List<Map<String, Object>> children = (List<Map<String, Object>>) component.get("Components");
        if (children != null) {
            for (Map<String, Object> child : children) {
                pairs.addAll(returnANDParentChildNOK(child, champsPere, champsFils, valeursPere, valeursFils));
            }
        }

        return pairs;
    }

    private boolean checkANDFor(Map<String, Object> component, List<String> champs, List<List<String>> valeurs) {
        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            boolean allCriteriaMet = false;
            for (Map.Entry<String, Object> entry : component.entrySet()) {
                if (champ.equals(entry.getKey()) && valeur.contains(entry.getValue())) {
                    allCriteriaMet = true;
                } else if (entry.getValue() instanceof Map) {
                    if (checkANDFor((Map<String, Object>) entry.getValue(), champs, valeurs)) {
                        allCriteriaMet = true;
                    } else {
                        allCriteriaMet = false;
                        break;
                    }
                } else if (entry.getValue() instanceof List) {
                    boolean anyInnerCriteriaMet = false;
                    for (Object item : (List<?>) entry.getValue()) {
                        if (item instanceof Map && checkANDFor((Map<String, Object>) item, champs, valeurs)) {
                            anyInnerCriteriaMet = true;
                            break;
                        }
                    }
                    allCriteriaMet = anyInnerCriteriaMet;
                }
            }

            if (!allCriteriaMet) {
                System.out.println("[WHEN][checkANDFor]  Au moins un critère est NOK : " + champ);
                return false;
            }
        }

        System.out.println("[WHEN][checkANDFor] Tous les critères sont remplis pour au moins un composant! ");
        return true;
    }

    private boolean checkORFor(Map<String, Object> component, List<String> champs, List<List<String>> valeurs) {
        String ruleName = "Vérification sur tous les éléments sur plusieurs critères multiples (OU)"; // Identifiant de règle prédéfini

        for (int i = 0; i < champs.size(); i++) {
            String champ = champs.get(i);
            List<String> valeur = valeurs.get(i);

            for (Map.Entry<String, Object> entry : component.entrySet()) {
                if (champ.equals(entry.getKey()) && valeur.contains(entry.getValue())) {
                    System.out.println("Règle : " + ruleName + ": CheckORFor : Log - Found matching entry: " + entry.getKey() + " : " + entry.getValue());
                    return true;
                } else if (entry.getValue() instanceof Map) {
                    if (checkORFor((Map<String, Object>) entry.getValue(), champs, valeurs)) {
                        return true;
                    }
                } else if (entry.getValue() instanceof List) {
                    for (Object item : (List<?>) entry.getValue()) {
                        if (item instanceof Map && checkORFor((Map<String, Object>) item, champs, valeurs)) {
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("Règle : " + ruleName + ": CheckORFor : Log - No matching entry found.");
        return false;
    }

}

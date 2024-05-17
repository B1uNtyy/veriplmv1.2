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

import java.util.List;
import java.util.Map;

public class Component {

    String IsLogisticAssembly;
    String RootNumber;
    String TechnicalClassification;
    String ChangeStatus;
    String FrLabel;
    String Version;
    String SoftType;
    String EnLabel;
    String Spareable;
    String Number;
    String Make;
    String Repairable;
    String DefaultTraceCode;
    String Cage;
    String DefaultUnit;
    String ProductMaturity;
    String SoftwareBuildId;
    String ReuseMode;
    String EndItem;
    String ItemType;
    String ObsolescenceStatus;
    String BaseTrackingIdentifier;
    String Revision;
    String Name;
    String CheckoutState;
    String FunctionalStatus;
    String State;
    String AuthorizationCode;
    String ItemCategory;
    String LogisticStatus;
    String DefaultTracedBy;

    List<Document> describedBy;
    List<Map<String, Object>> Components;

    public Component() {

    }

    public List<Document> getDescribedBy() {
        return describedBy;
    }

    public void setDescribedBy(List<Document> describedBy) {
        this.describedBy = describedBy;
    }

    public String getDefaultTracedBy() {
        return DefaultTracedBy;
    }

    public void setDefaultTracedBy(String DefaultTracedBy) {
        this.DefaultTracedBy = DefaultTracedBy;
    }

    public String getItemCategory() {
        return ItemCategory;
    }

    public void setItemCategory(String ItemCategory) {
        this.ItemCategory = ItemCategory;
    }

    public String getAuthorizationCode() {
        return AuthorizationCode;
    }

    public void setAuthorizationCode(String AuthorizationCode) {
        this.AuthorizationCode = AuthorizationCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getFunctionalStatus() {
        return FunctionalStatus;
    }

    public void setFunctionalStatus(String FunctionalStatus) {
        this.FunctionalStatus = FunctionalStatus;
    }

    public String getCheckoutState() {
        return CheckoutState;
    }

    public void setCheckoutState(String CheckoutState) {
        this.CheckoutState = CheckoutState;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRevision() {
        return Revision;
    }

    public void setRevision(String Revision) {
        this.Revision = Revision;
    }

    public String getBaseTrackingIdentifier() {
        return BaseTrackingIdentifier;
    }

    public void setBaseTrackingIdentifier(String BaseTrackingIdentifier) {
        this.BaseTrackingIdentifier = BaseTrackingIdentifier;
    }

    public String getObsolescenceStatus() {
        return ObsolescenceStatus;
    }

    public void setObsolescenceStatus(String ObsolescenceStatus) {
        this.ObsolescenceStatus = ObsolescenceStatus;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String ItemType) {
        this.ItemType = ItemType;
    }

    public String getEndItem() {
        return EndItem;
    }

    public void setEndItem(String EndItem) {
        this.EndItem = EndItem;
    }

    public String getReuseMode() {
        return ReuseMode;
    }

    public void setReuseMode(String ReuseMode) {
        this.ReuseMode = ReuseMode;
    }

    public String getSoftwareBuildId() {
        return SoftwareBuildId;
    }

    public void setSoftwareBuildId(String SoftwareBuildId) {
        this.SoftwareBuildId = SoftwareBuildId;
    }

    public String getProductMaturity() {
        return ProductMaturity;
    }

    public void setProductMaturity(String ProductMaturity) {
        this.ProductMaturity = ProductMaturity;
    }

    public String getDefaultUnit() {
        return DefaultUnit;
    }

    public void setDefaultUnit(String DefaultUnit) {
        this.DefaultUnit = DefaultUnit;
    }

    public String getCage() {
        return Cage;
    }

    public void setCage(String Cage) {
        this.Cage = Cage;
    }

    public String getDefaultTraceCode() {
        return DefaultTraceCode;
    }

    public void setDefaultTraceCode(String DefaultTraceCode) {
        this.DefaultTraceCode = DefaultTraceCode;
    }

    public String getRepairable() {
        return Repairable;
    }

    public void setRepairable(String Repairable) {
        this.Repairable = Repairable;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getSpareable() {
        return Spareable;
    }

    public void setSpareable(String Spareable) {
        this.Spareable = Spareable;
    }

    public String getEnLabel() {
        return EnLabel;
    }

    public void setEnLabel(String EnLabel) {
        this.EnLabel = EnLabel;
    }

    public String getSoftType() {
        return SoftType;
    }

    public void setSoftType(String SoftType) {
        this.SoftType = SoftType;
    }

    public String getFrLabel() {
        return FrLabel;
    }

    public void setFrLabel(String FrLabel) {
        this.FrLabel = FrLabel;
    }

    public String getChangeStatus() {
        return ChangeStatus;
    }

    public void setChangeStatus(String ChangeStatus) {
        this.ChangeStatus = ChangeStatus;
    }

    public String getTechnicalClassification() {
        return TechnicalClassification;
    }

    public void setTechnicalClassification(String TechnicalClassification) {
        this.TechnicalClassification = TechnicalClassification;
    }

    public String getRootNumber() {
        return RootNumber;
    }

    public void setRootNumber(String RootNumber) {
        this.RootNumber = RootNumber;
    }

    public String getIsLogisticAssembly() {
        return IsLogisticAssembly;
    }

    public void setIsLogisticAssembly(String IsLogisticAssembly) {
        this.IsLogisticAssembly = IsLogisticAssembly;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getLogisticStatus() {
        return LogisticStatus;
    }

    public void setLogisticStatus(String LogisticStatus) {
        this.LogisticStatus = LogisticStatus;
    }

    public List<Map<String, Object>> getComponents() {
        return Components;
    }

    public void setComponents(List<Map<String, Object>> components) {
        this.Components = components;
    }

}

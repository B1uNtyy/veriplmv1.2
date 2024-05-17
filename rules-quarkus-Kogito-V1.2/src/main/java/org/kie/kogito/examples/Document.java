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

public class Document {

    String Latest;
    String RootNumber;
    String FrLabel;
    String Version;
    String SoftType;
    String EnLabel;
    String Number;
    String GeneralStatus;
    String DTC;
    String Cage;
    String DocumentFamily;
    String Revision;
    String Title;
    String Name;
    String CheckoutState;
    String State;

    public Document() {

    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getRevision() {
        return Revision;
    }

    public void setRevision(String Revision) {
        this.Revision = Revision;
    }

    public String getDocumentFamily() {
        return DocumentFamily;
    }

    public void setDocumentFamily(String DocumentFamily) {
        this.DocumentFamily = DocumentFamily;
    }

    public String getCage() {
        return Cage;
    }

    public void setCage(String Cage) {
        this.Cage = Cage;
    }

    public String getDTC() {
        return DTC;
    }

    public void setDTC(String DTC) {
        this.DTC = DTC;
    }

    public String getGeneralStatus() {
        return GeneralStatus;
    }

    public void setGeneralStatus(String GeneralStatus) {
        this.GeneralStatus = GeneralStatus;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
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

    public String getLatest() {
        return Latest;
    }

    public void setLatest(String Latest) {
        this.Latest = Latest;
    }

    public String getRootNumber() {
        return RootNumber;
    }

    public void setRootNumber(String RootNumber) {
        this.RootNumber = RootNumber;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

}

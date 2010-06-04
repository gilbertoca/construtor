/*
 * Copyright 2004-2006 Takashi Okamoto, Malcolm A. Edgar
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

package org.apache.click.extras.jpa.test;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehicleType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic
    private String vType;
    @Basic
    private String manufacturer;
    @Basic
    private String model;

    public VehicleType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleType(String vType) {
        this.vType = vType;
    }

    public VehicleType(String vType, String manufacturer, String model) {
        this.vType = vType;
        this.manufacturer = manufacturer;
        this.model = model;
    }


    public String getVtype() {
        return vType;
    }

    public void setVtype(String vType) {
        this.vType = vType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vType != null ? vType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehicleType)) {
            return false;
        }
        VehicleType other = (VehicleType) object;
        if ((this.vType == null && other.vType != null) || (this.vType != null && !this.vType.equals(other.vType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.VehicleType[V_TYPE=" + vType + "]";
    }

}

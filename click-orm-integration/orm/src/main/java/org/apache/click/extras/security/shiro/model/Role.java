package org.apache.click.extras.security.shiro.model;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;

/**
 * Model object that represents a security role.
 */
@Entity
@Table(name="shiro_role")
@Cacheable(true)
@NamedQueries({
        @NamedQuery(
                name = Role.FIND_BY_ROLE_NAME,
                query = "SELECT r FROM Role r WHERE r.name = :name "
        )
})
public class Role {
    public static final String FIND_BY_ROLE_NAME = "User.findByRoleName";
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SHIRO_ROLE_SEQ")
    @TableGenerator(name="SHIRO_ROLE_SEQ", table="SHIRO_SEQUENCE", pkColumnName="SEQ_NAME",
        valueColumnName="SEQ_COUNT", pkColumnValue="SHIRO_ROLE_SEQ")
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 255)
    private String description;
    @ElementCollection
    @CollectionTable(
             name="shiro_role_permission",
             joinColumns=@JoinColumn(name="ROLE_ID",referencedColumnName="ID"))
    @Column(name="permission")
    private Set<String> permissions = new HashSet<String>();

    protected Role() { }

    public Role(String name) {
        this.name = name;
    }
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
    /**
     * Adds a permission for the role
     *
     * @param String the permission
     */
    public void addPermission(String permission) {
        getPermissions().add(permission);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Role{" + " id=" + id + " name=" + name + " description=" + description + " permissions=" + permissions + '}';
    }

}



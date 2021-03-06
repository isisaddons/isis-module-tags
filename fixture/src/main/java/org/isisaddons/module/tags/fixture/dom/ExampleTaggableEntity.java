/*
 *  Copyright 2013~2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.module.tags.fixture.dom;

import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.isisaddons.module.tags.dom.Tag;
import org.isisaddons.module.tags.dom.Tags;
import org.apache.isis.applib.annotation.*;

/**
 * Represents a general purpose mechanism for tagging (or labelling) any entity with a named
 * (string) value.
 */
@javax.jdo.annotations.PersistenceCapable(identityType= IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy= IdGeneratorStrategy.NATIVE,
        column="id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Uniques({
        @javax.jdo.annotations.Unique(
                name = "Tag_name_UNQ", members = { "name" })
})
@MemberGroupLayout(columnSpans = {4,4,4,12},
        left = {"General"},
        middle = {"Tagged"},
        right = {"Value"}
)
public class ExampleTaggableEntity {

    private static final String TAG_NAME_BRAND = "Brand";
    private static final String TAG_NAME_SECTOR = "Sector";

    // //////////////////////////////////////

    private String name;

    @javax.jdo.annotations.Column(allowsNull="false")
    @Title(sequence="1")
    @MemberOrder(sequence="1")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    // //////////////////////////////////////

    private Tag brandTag;

    @javax.jdo.annotations.Column(name = "BRANDTAG_ID", allowsNull="true")
    @Property(
            hidden = Where.EVERYWHERE
    )
    public Tag getBrandTag() {
        return brandTag;
    }

    public void setBrandTag(final Tag brandTag) {
        this.brandTag = brandTag;
    }

    // //////////////////////////////////////


    @javax.jdo.annotations.NotPersistent
    @MemberOrder(sequence="2")
    public String getBrand() {
        final Tag existingTag = getBrandTag();
        return existingTag != null ? existingTag.getValue() : null;
    }

    public void setBrand(final String brand) {
        final Tag existingTag = getBrandTag();
        Tag tag = tags.tagFor(this, existingTag, TAG_NAME_BRAND, brand);
        setBrandTag(tag);
    }

    public List<String> choicesBrand() {
        return tags.choices(this, TAG_NAME_BRAND);
    }

    // //////////////////////////////////////

    @MemberOrder(name="brand", sequence = "2")
    public ExampleTaggableEntity updateBrand(
            @ParameterLayout(named="Tag") @Parameter(optionality = Optionality.OPTIONAL)
            final String brand) {
        setBrand(brand);
        return this;
    }

    public String default0UpdateBrand() {
        return getBrand();
    }

    public List<String> choices0UpdateBrand() {
        return tags.choices(this, TAG_NAME_BRAND);
    }

    // //////////////////////////////////////

    @MemberOrder(name="brand", sequence = "1")
    public ExampleTaggableEntity newBrand(
            @ParameterLayout(named="Tag") @Parameter(optionality = Optionality.OPTIONAL)
            final String brand) {
        setBrand(brand);
        return this;
    }

    public String default0NewBrand() {
        return getBrand();
    }


    // //////////////////////////////////////

    private Tag sectorTag;

    @javax.jdo.annotations.Column(name = "SECTORTAG_ID", allowsNull="true")
    @Property(
            hidden = Where.EVERYWHERE
    )
    public Tag getSectorTag() {
        return sectorTag;
    }

    public void setSectorTag(final Tag sectorTag) {
        this.sectorTag = sectorTag;
    }

    // //////////////////////////////////////

    @javax.jdo.annotations.NotPersistent
    @MemberOrder(sequence="2")
    public String getSector() {
        final Tag existingTag = getSectorTag();
        return existingTag != null ? existingTag.getValue() : null;
    }

    public void setSector(final String sector) {
        final Tag existingTag = getSectorTag();
        Tag tag = tags.tagFor(this, existingTag, TAG_NAME_SECTOR, sector);
        setSectorTag(tag);
    }

    public List<String> choicesSector() {
        return tags.choices(this, TAG_NAME_SECTOR);
    }

    // //////////////////////////////////////

    @MemberOrder(name="sector", sequence = "2")
    public ExampleTaggableEntity updateSector(
            @ParameterLayout(named="Tag") @Parameter(optionality = Optionality.OPTIONAL)
            final String sector) {
        setSector(sector);
        return this;
    }

    public String default0UpdateSector() {
        return getSector();
    }

    public List<String> choices0UpdateSector() {
        return tags.choices(this, TAG_NAME_SECTOR);
    }

    // //////////////////////////////////////

    @MemberOrder(name="sector", sequence = "1")
    public ExampleTaggableEntity newSector(
            @ParameterLayout(named="Tag") @Parameter(optionality = Optionality.OPTIONAL)
            final String sector) {
        setSector(sector);
        return this;
    }

    public String default0NewSector() {
        return getSector();
    }


    // //////////////////////////////////////

    @javax.inject.Inject
    private Tags tags;

}

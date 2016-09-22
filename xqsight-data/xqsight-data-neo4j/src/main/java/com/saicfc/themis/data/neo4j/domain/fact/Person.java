package com.saicfc.themis.data.neo4j.domain.fact;

import com.saicfc.themis.data.neo4j.domain.Entity;
import com.saicfc.themis.data.neo4j.domain.RelationshipConstants;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GTaurus on 2016/4/5.
 */
@NodeEntity(label = "Person")
public class Person extends Entity {

    private String certNo;
    private String certType;
    private String name;

    @Relationship(type = RelationshipConstants.KNOWS)
    private Set<Person> acquaintance = new HashSet<>();

    @Relationship(type = RelationshipConstants.CHILD_OF)
    private Set<Person> parents = new HashSet<>();

    @Relationship(type = RelationshipConstants.CHILD_OF, direction = Relationship.INCOMING)
    private Set<Person> childs = new HashSet<>();

    @Relationship(type = RelationshipConstants.SPOUSE_OF, direction = Relationship.UNDIRECTED)
    private Set<Person> spouse = new HashSet<>();

    @Relationship(type = RelationshipConstants.OTHER_RELATIVE_OF)
    private Set<Person> otherRelatives = new HashSet<>();

    @Relationship(type = RelationshipConstants.OWNS)
    private Set<Phone> phones = new HashSet<>();

    @Relationship(type = RelationshipConstants.HOLDS)
    private Set<BankCard> bankCards = new HashSet<>();

    @Relationship(type = RelationshipConstants.LIVES)
    private Set<Location> locations = new HashSet<>();

    public void knows(Person... p) {
        for (int i = 0; i < p.length; i++) {
            this.acquaintance.add(p[i]);
        }
    }

    public void childOf(Person... p) {
        for (int i = 0; i < p.length; i++) {
            this.parents.add(p[i]);
        }
    }

    public void parentOf(Person... p) {
        for (int i = 0; i < p.length; i++) {
            this.childs.add(p[i]);
        }
    }

    public void marries(Person... p) {
        for (int i = 0; i < p.length; i++) {
            this.spouse.add(p[i]);
        }
    }

    public void otherRelatieOf(Person... p) {
        for (int i = 0; i < p.length; i++) {
            this.otherRelatives.add(p[i]);
        }
    }


    public void owns(Phone... phone) {
        for (int i = 0; i < phone.length; i++) {
            this.phones.add(phone[i]);
        }
    }

    public void holds(BankCard... cards) {
        for (int i = 0; i < cards.length; i++)
            this.bankCards.add(cards[i]);
    }

    public void lives(Location... locs) {
        for (int i = 0; i < locs.length; i++) this.locations.add(locs[i]);
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person && this.certNo.equalsIgnoreCase(((Person) obj).getCertNo())
                && this.name.equalsIgnoreCase(((Person) obj).getName())
                && this.certType.equals(((Person) obj).getCertType());
    }

    /* get and set */
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getAcquaintance() {
        return acquaintance;
    }

    public void setAcquaintance(Set<Person> acquaintance) {
        this.acquaintance = acquaintance;
    }

    public Set<Person> getParents() {
        return parents;
    }

    public void setParents(Set<Person> parents) {
        this.parents = parents;
    }

    public Set<Person> getChilds() {
        return childs;
    }

    public void setChilds(Set<Person> childs) {
        this.childs = childs;
    }

    public Set<Person> getSpouse() {
        return spouse;
    }

    public void setSpouse(Set<Person> spouse) {
        this.spouse = spouse;
    }

    public Set<Person> getOtherRelatives() {
        return otherRelatives;
    }

    public void setOtherRelatives(Set<Person> otherRelatives) {
        this.otherRelatives = otherRelatives;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(Set<BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}

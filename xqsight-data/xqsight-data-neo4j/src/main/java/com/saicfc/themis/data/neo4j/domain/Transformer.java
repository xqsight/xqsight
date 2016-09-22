package com.saicfc.themis.data.neo4j.domain;

import com.saicfc.themis.api.models.PersonalInfo;
import com.saicfc.themis.api.models.SocialRelationType;
import com.saicfc.themis.data.neo4j.domain.fact.BankCard;
import com.saicfc.themis.data.neo4j.domain.fact.Location;
import com.saicfc.themis.data.neo4j.domain.fact.Person;
import com.saicfc.themis.data.neo4j.domain.fact.Phone;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GTaurus on 2016/4/7.
 */
public class Transformer {

    public static Person transform(PersonalInfo info) {
        Person p = new Person();
        p.setCertNo(info.getCertNo());
        p.setCertType(info.getCertType());
        p.setName(info.getName());
        p.lives(getLocation(info));
        p.holds(getBankCard(info));
        p.childOf(getRelatives(info, SocialRelationType.PARENT));
        p.parentOf(getRelatives(info, SocialRelationType.CHILD));
        p.marries(getRelatives(info, SocialRelationType.SPOUSE));
        p.knows(getRelatives(info, SocialRelationType.KNOWS));
        p.otherRelatieOf(getRelatives(info, SocialRelationType.OTHER_RELATIVE));
        p.owns(getPhone(info));
        return p;
    }

    private static Phone[] getPhone(PersonalInfo info) {
        List<Phone> phones = new ArrayList<>();
        if(StringUtils.isNotBlank(info.getCellPhone())){
            Phone phone = new Phone();
            phone.setNumber(info.getCellPhone());
            phones.add(phone);
        }
        return phones.toArray(new Phone[phones.size()]);
    }

    private static Person[] getRelatives(PersonalInfo info, SocialRelationType type) {
        List<Person> persons = new ArrayList<>();
        switch (type) {
            case PARENT:
                if(info.getFather() != null){
                    persons.add(transform(info.getFather()));
                }
                if(info.getMother() != null){
                    persons.add(transform(info.getMother()));
                }
                break;
            case CHILD:
                if(info.getChild() != null){
                    persons.add(transform(info.getChild()));
                }
                break;
            case SPOUSE:
                if(info.getSpouse() != null){
                    persons.add(transform(info.getSpouse()));
                }
                break;
            case OTHER_RELATIVE:
                if(info.getOtherRelative() != null){
                    persons.add(transform(info.getOtherRelative()));
                }
                break;
            case KNOWS:
                if(info.getOther() != null){
                    persons.add(transform(info.getOther()));
                }
                break;
            default:

        }
        return persons.toArray(new Person[persons.size()]);
    }

    private static BankCard[] getBankCard(PersonalInfo info) {
        List<BankCard> cards = new ArrayList<>();
        if(StringUtils.isNotBlank(info.getBankCardNo())){
            BankCard card = new BankCard();
            card.setNumber(info.getBankCardNo());
            cards.add(card);
        }
        return cards.toArray(new BankCard[cards.size()]);
    }

    private static Location[] getLocation(PersonalInfo info) {
        List<Location> locs = new ArrayList<>();
        if(StringUtils.isNotBlank(info.getHomeAddress())){
            locs.add(new Location(info.getHomeAddress(), LocationTypeConstants.HOME));
        }
        if(StringUtils.isNotBlank(info.getRegAddress())){
            locs.add(new Location(info.getRegAddress(), LocationTypeConstants.REG));
        }
        if(StringUtils.isNotBlank(info.getCompanyAddress())){
            locs.add(new Location(info.getCompanyAddress(), LocationTypeConstants.WORK));
        }
        if(StringUtils.isNotBlank(info.getContactAddress())){
            locs.add(new Location(info.getContactAddress(), LocationTypeConstants.OTHER));
        }

        return locs.toArray(new Location[locs.size()]);
    }
}

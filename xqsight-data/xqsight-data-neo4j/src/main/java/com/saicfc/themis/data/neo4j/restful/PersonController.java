package com.saicfc.themis.data.neo4j.restful;

import com.saicfc.themis.data.neo4j.domain.Transformer;
import com.saicfc.themis.data.neo4j.domain.fact.BankCard;
import com.saicfc.themis.data.neo4j.domain.fact.Person;
import com.saicfc.themis.data.neo4j.model.CertType;
import com.saicfc.themis.data.neo4j.model.PersonalInfo;
import com.saicfc.themis.data.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by GTaurus on 2016/4/6.
 */
@RestController
@RequestMapping("/person")
public class PersonController extends AbstractController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Object save(@RequestBody PersonalInfo info) {

        personRepository.save(Transformer.transform(info));
        Map<String, String> resp = new HashMap<>();
        resp.put("returnCode", "");
        resp.put("returnDesc", "111");
        return resp;
    }

    @RequestMapping(value = "/relate", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public Object relate(@RequestBody PersonalInfo info) {
        Person person = Transformer.transform(info);

        return null;
    }

    @RequestMapping(value = "/findByIdNo/{idNo}", method = RequestMethod.GET, produces = "application/json")
    public Object findByIdNo(@PathVariable("idNo") String idNo) {
        List<Person> persons  = personRepository.findAllByCert(idNo, CertType.NATIONAL_ID.type());
        if (persons != null) {
            Person person = persons.get(0);
            Set<BankCard> bankCards = person.getBankCards();
            if (bankCards != null) {
                if (bankCards.iterator().hasNext() ){
                    System.out.println(bankCards.iterator().next().getBankCode());
                }
            }

        }
        return persons;
    }

}

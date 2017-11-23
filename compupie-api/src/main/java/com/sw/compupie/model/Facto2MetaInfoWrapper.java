package com.sw.compupie.model;

import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoImpl.Load_Factor2MetaInfo;

public class Facto2MetaInfoWrapper {

	private String[] category = { "Food/Nutrition", "Shelter", "Employment", "Economic Resources", "Transportation",
			"Health Problems", "Safety Problems", "Social Service Problems", "Religious Group Problems",
			"Community Group Problems", "Education and Training", "Judicial and Legal", "Affectional Support" };

	public List<Factor2Meta> factor2meta = new ArrayList<Factor2Meta>();

	public Facto2MetaInfoWrapper() {
		for (String cat : category) {
			Load_Factor2MetaInfo met = new Load_Factor2MetaInfo();
			Factor2Meta fact = new Factor2Meta();
			fact.setCategoryName(cat);
			fact.setFactor2Meta(met.getAllProblemsByCategory(cat));
			factor2meta.add(fact);
		}
	}

}

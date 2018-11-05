package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CataMvc4Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CataMvc4Application.class, args);
		ProduitRepository produitrepository = ctx.getBean(ProduitRepository.class);
		produitrepository.save(new Produit("LX 234", 670, 90));
		produitrepository.save(new Produit("Ordi hp 322", 670, 90));
		produitrepository.save(new Produit("impr epson 32", 670, 90));
		produitrepository.save(new Produit("impr HP 65", 670, 90));
		
		produitrepository.findAll().forEach(p->System.out.println(p.getDesignation()));
	}
}

package org.sid.web;


import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository pr;
	
	@RequestMapping(value="/index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0")int p, 
			@RequestParam(name="size", defaultValue="5")int s,
			@RequestParam(name="motCle", defaultValue="")String mc) {
		
		Page<Produit> pp = pr.chercher("%"+mc+"%",new PageRequest(p,s));
		model.addAttribute("listProduits",pp.getContent());
		int[] pages= new int[pp.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "produits";
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Long id, String motCle, int page,int size){
		pr.deleteById(id);
		return "redirect:/index?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
}

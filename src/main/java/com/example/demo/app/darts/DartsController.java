package com.example.demo.app.darts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Darts;
import com.example.demo.service.DartsService;

@Controller
@RequestMapping("/darts")
public class DartsController {
	
	private final DartsService dartsService;
	
	@Autowired
	public DartsController(DartsService dartsService) {
		this.dartsService = dartsService;
	}

	@GetMapping
	public String index(Model model) {

		model.addAttribute("title", "コソ練COUNT UP");
		
		return "darts/index";
	}
	
	@PostMapping("/complete")
	public String complete(@Validated DartsForm dartsForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "ばりでーしょんえらー");
			
			return "darts/index";
		}
		
		Darts darts = new Darts();
		darts.setTotalScore(dartsForm.getTotalScore());
		
		dartsService.save(darts);
		
		redirectAttributes.addFlashAttribute("complete", "データを送信しました");
		
		return "redirect:/darts";
	}
}

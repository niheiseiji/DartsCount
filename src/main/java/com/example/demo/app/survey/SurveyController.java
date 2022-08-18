package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
    private Map<String, String> radioGender;

    private Map<String, String> initRadioGender() {
        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("0", "男性");
        radio.put("1", "女性");
        return radio;
    }
    
	private final SurveyService surveyService;
    
	@Autowired
	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
 
	@GetMapping
	public String index(Model model) {
		List<Survey> list = surveyService.getAll();
		System.out.println(list);
		
		model.addAttribute("surveyList", list);
		model.addAttribute("title", "アンケート一覧");
		
		return "survey/index";
	}
    
	@GetMapping("/form")
	public String form(
			SurveyForm surveyForm,
			Model model,
			@ModelAttribute("complete") String complete
			) {
		model.addAttribute("title", "アンケートフォーム");
        radioGender = initRadioGender();
        model.addAttribute("radioGender", radioGender);
		
		return "survey/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(SurveyForm surveyForm, Model model) {
        radioGender = initRadioGender();
        model.addAttribute("radioGender", radioGender);
		model.addAttribute("title", "survey form POST");
		
		return "survey/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケートフォームエラーでもどった");
	        radioGender = initRadioGender();
	        model.addAttribute("radioGender", radioGender);
		
			return "survey/form";
		}
		model.addAttribute("title", "確認ページ");
		
		return "survey/confirm";
	}

	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "確認ページから完了でエラー");
			
			return "survey/form";
		}
		
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setGender(surveyForm.getGender());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		
		surveyService.save(survey);
		
		redirectAttributes.addFlashAttribute("complete", "アンケートの入力完了");
		
		return "redirect:/survey/form";
	}
}

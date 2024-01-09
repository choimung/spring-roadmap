package com.example.demo.web;

import com.example.demo.domain.Item;
import com.example.demo.domain.MemberRepository;
import com.example.demo.web.validation.ItemSaveForm;
import com.example.demo.web.validation.ItemUpdateForm;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//TODO 1.국제화
//TODO 2.Validation


@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final MemberRepository memberRepository;

    /* 아이템 목록 페이지 시작 */
    @GetMapping
    public String items(Model model) {
        List<Item> items = memberRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }
    /* 아이템 목록 페이지 종료 */

    /* 아이템 상세 페이지 시작 */
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model){
        Item findItem = memberRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/item";
    }
    /* 아이템 상세 페이지 종료 */


    /* 아이템 등록폼 ~ 아이템 등록 시작 */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute(new Item());
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if(form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }


        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "basic/addForm";
        }

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
        Item savedItem = memberRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }
    /* 아이템 등록폼 ~ 아이템 등록 종료 */

    /* 아이템 수정폼 ~ 아이템 수정 시작 */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model) {
        Item findItem = memberRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "basic/editForm";
        }

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());

        memberRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
    /* 아이템 수정폼 ~ 아이템 수정 종료 */


    @PostConstruct
    private void init() {
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 2000, 20);

        memberRepository.save(itemA);
        memberRepository.save(itemB);
    }
}

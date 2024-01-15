package hello.mvczip.web.controller.item;

import hello.mvczip.domain.item.Item;
import hello.mvczip.domain.item.ItemRepository;
import hello.mvczip.web.controller.item.form.ItemSaveForm;
import hello.mvczip.web.controller.item.form.ItemUpdateForm;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new ItemSaveForm());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(form.getPrice() != null || form.getQuantity() != null) {
            int result = form.getPrice() * form.getQuantity();
            if(result < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, "최소 주문 금액은 10,000원 입니다.");
            }
        }

        if(bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            return "items/addForm";
        }

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {


        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "items/editForm";
        }

        Item findItem = itemRepository.findById(itemId);

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());

        itemRepository.updateItem(findItem.getId(), item);
        return "redirect:/items/{itemId}";
    }

}

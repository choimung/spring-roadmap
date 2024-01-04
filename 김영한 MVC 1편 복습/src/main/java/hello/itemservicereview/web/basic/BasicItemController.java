package hello.itemservicereview.web.basic;

import hello.itemservicereview.domain.Item;
import hello.itemservicereview.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }


    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

////    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
//        Item saved = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", saved.getId());
//        return "redirect:/basic/items/{itemId}";
//    }

//    @PostMapping("/add")
//    public String addItemV1(HttpServletRequest request, HttpServletResponse response, Model model) {
//        String itemName = request.getParameter("itemName");
//        int price = Integer.parseInt(request.getParameter("price"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        Item item = new Item(itemName, price, quantity);
//        model.addAttribute("item", item);
//        System.out.println("itemName = " + itemName);
//        System.out.println("price = " + price);
//        System.out.println("quantity = " + quantity);
//        itemRepository.save(item);
//        return "redirect:/basic/items/" + item.getId();
//    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}

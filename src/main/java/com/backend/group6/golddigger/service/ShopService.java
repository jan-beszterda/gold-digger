package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ShopDAO;
import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.model.Shop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {
    ShopDAO shopDAO;

    public ShopService(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public List<Shop> getAllShops() {
        return (List<Shop>) shopDAO.getAllShops();
    }

    public Shop getShopById(Integer id) {
        return shopDAO.getShopById(id).orElse(null);
    }

    public void addShop(Shop shop) {
        shopDAO.addShop(shop);
    }

    public Item sellItem(Integer itemId) {
        Item anItem = getAllShops()
                .stream()
                .findAny()
                .get()
                .getShopInventory()
                .stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .get();
        Item itemToBuy = null;
        if (anItem instanceof FoodItem) {
            itemToBuy = new FoodItem();
            itemToBuy.setItemName(anItem.getItemName());
            itemToBuy.setItemPrice(anItem.getItemPrice());
            ((FoodItem) itemToBuy).setHealthEffect(((FoodItem) anItem).getHealthEffect());
            ((FoodItem) itemToBuy).setWeight(((FoodItem) anItem).getWeight());
        } else if (anItem instanceof Pickaxe) {
            itemToBuy = new Pickaxe();
            itemToBuy.setItemName(anItem.getItemName());
            itemToBuy.setItemPrice(anItem.getItemPrice());
            ((Pickaxe) itemToBuy).setStrength(((Pickaxe) anItem).getStrength());
            ((Pickaxe) itemToBuy).setCondition(((Pickaxe) anItem).getCondition());
        }
        return itemToBuy;
    }
}

package com.elane.learning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.elane.learning.mapstruct.Item;
import com.elane.learning.mapstruct.ItemConverter;
import com.elane.learning.mapstruct.Sku;
import com.elane.learning.mapstruct.SkuDTO;
import org.junit.Test;

public class ItemConverterTest {
  @Test
  public void test() {
    Item item = new Item(1L, "iPhone X");
    Sku sku = new Sku(2L, "phone12345", 1000000);
    SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);
    assertNotNull(skuDTO);
    assertEquals(skuDTO.getSkuId(),sku.getId());
    assertEquals(skuDTO.getSkuCode(),sku.getCode());
    assertEquals(skuDTO.getSkuPrice(),sku.getPrice());
    assertEquals(skuDTO.getItemId(),item.getId());
    assertEquals(skuDTO.getItemName(),item.getTitle());
  }

}

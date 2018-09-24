package sanaebadi.ir.tandorosti.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import sanaebadi.ir.tandorosti.BeansModel.Bran;
import sanaebadi.ir.tandorosti.BeansModel.Bread;
import sanaebadi.ir.tandorosti.BeansModel.Flour;
import sanaebadi.ir.tandorosti.BeansModel.Grain;
import sanaebadi.ir.tandorosti.BeansModel.Rice;
import sanaebadi.ir.tandorosti.BeansModel.Spaghetti;
import sanaebadi.ir.tandorosti.BeveragesModel.Distillates;
import sanaebadi.ir.tandorosti.BeveragesModel.Juice;
import sanaebadi.ir.tandorosti.BeveragesModel.Tea;
import sanaebadi.ir.tandorosti.ClothesModel.FeltProducts;
import sanaebadi.ir.tandorosti.ClothesModel.Kids;
import sanaebadi.ir.tandorosti.ClothesModel.Mens;
import sanaebadi.ir.tandorosti.ClothesModel.Womens;
import sanaebadi.ir.tandorosti.CosmeticsModel.Cosmetic;
import sanaebadi.ir.tandorosti.CosmeticsModel.MedicinalPlants;
import sanaebadi.ir.tandorosti.CosmeticsModel.Sanitary;
import sanaebadi.ir.tandorosti.JunkFoodModel.Biscuits;
import sanaebadi.ir.tandorosti.JunkFoodModel.DriedFruit;
import sanaebadi.ir.tandorosti.JunkFoodModel.Nuts;
import sanaebadi.ir.tandorosti.JunkFoodModel.OtherSnacks;
import sanaebadi.ir.tandorosti.OIiModel.Olive;
import sanaebadi.ir.tandorosti.OIiModel.Peanut;
import sanaebadi.ir.tandorosti.OIiModel.Sesame;
import sanaebadi.ir.tandorosti.OIiModel.SunFlower;
import sanaebadi.ir.tandorosti.SeasonedModel.HoneyAndJam;
import sanaebadi.ir.tandorosti.SeasonedModel.PasteAndSauce;
import sanaebadi.ir.tandorosti.SeasonedModel.Pickles;
import sanaebadi.ir.tandorosti.SeasonedModel.Spice;
import sanaebadi.ir.tandorosti.SeasonedModel.Sugar;
import sanaebadi.ir.tandorosti.SeasonedModel.Sweets;
import sanaebadi.ir.tandorosti.model.BestPro;
import sanaebadi.ir.tandorosti.NewsModel.Dairy;
import sanaebadi.ir.tandorosti.NewsModel.Fruit;
import sanaebadi.ir.tandorosti.model.NewPro;
import sanaebadi.ir.tandorosti.NewsModel.Protein;
import sanaebadi.ir.tandorosti.model.SamePro;
import sanaebadi.ir.tandorosti.NewsModel.Siffy;
import sanaebadi.ir.tandorosti.NewsModel.Vegetables;

/**
 * Created by sanaebadi on 3/22/18.
 */

public interface ApiInterface {

  @POST("get-new-pro-data.php")
  Call<List<NewPro>> getNewPro();

  @POST("get-best-pro-data.php")
  Call<List<BestPro>> getBestPro();

  @POST("get-same-pro-data.php")
  Call<List<SamePro>> getSamePro();

  @POST("get-dairy-frag-pro-data.php")
  Call<List<Dairy>> getDairyFragment();

  @POST("get-fruit-frag-pro-data.php")
  Call<List<Fruit>> getFruitFragment();

  @POST("get-protein-frag-pro-data.php")
  Call<List<Protein>> getProteinFragment();

  @POST("get-vegetables-frag-pro-data.php")
  Call<List<Vegetables>> getVegetablesFragment();

  @POST("get-siffy-frag-pro-data.php")
  Call<List<Siffy>> getSiffyFragment();

  @POST("get-bran-frag-pro-data.php")
  Call<List<Bran>> getBranFragment();

  @POST("get-bread-frag-pro-data.php")
  Call<List<Bread>> getBreadFragment();

  @POST("get-flour-frag-pro-data.php")
  Call<List<Flour>> getFlourFragment();

  @POST("get-grain-frag-pro-data.php")
  Call<List<Grain>> getGrainFragment();

  @POST("get-rice-frag-pro-data.php")
  Call<List<Rice>> getRiceFragment();

  @POST("get-spaghetti-frag-pro-data.php")
  Call<List<Spaghetti>> getSpaghettiFragment();

  @POST("get-distillates-frag-pro-data.php")
  Call<List<Distillates>> getDistillatesFragment();

  @POST("get-juice-frag-pro-data.php")
  Call<List<Juice>> getJuiceFragment();

  @POST("get-tea-frag-pro-data.php")
  Call<List<Tea>> getTeaFragment();

  @POST("get-biscuit-frag-pro-data.php")
  Call<List<Biscuits>> getBiscuitFragment();

  @POST("get-dried-fruit-frag-pro-data.php")
  Call<List<DriedFruit>> getDriedFruitFragment();

  @POST("get-nuts-frag-pro-data.php")
  Call<List<Nuts>> getNutsFragment();

  @POST("get-other-snacks-frag-pro-data.php")
  Call<List<OtherSnacks>> getOtherSnacksFragment();

  @POST("get-cosmetic-frag-pro-data.php")
  Call<List<Cosmetic>> getCosmeticFragment();

  @POST("get-medicinal-plants-frag-pro-data.php")
  Call<List<MedicinalPlants>> getMedicinalPlantFragment();

  @POST("get-sanitary-frag-pro-data.php")
  Call<List<Sanitary>> getSanitaryFragment();

  @POST("get-mens-wears-frag-pro-data.php")
  Call<List<Mens>> getMensFragment();

  @POST("get-women-dress-frag-pro-data.php")
  Call<List<Womens>> getWomensFragment();

  @POST("get-kids-cloth-frag-pro-data.php")
  Call<List<Kids>> getKidsFragment();

  @POST("get-felt-products-frag-pro-data.php")
  Call<List<FeltProducts>> getFeltProductsFragment();

  @POST("get-olive-oil-frag-pro-data.php")
  Call<List<Olive>> getOliveFragment();

  @POST("get-peanut-oil-frag-pro-data.php")
  Call<List<Peanut>> getPeanutFragment();

  @POST("get-sesame-oil-frag-pro-data.php")
  Call<List<Sesame>> getSesameFragment();

  @POST("get-sun-flowers-oil-frag-pro-data.php")
  Call<List<SunFlower>> getSunFlowrsFragment();

  @POST("get-honey-jam-frag-pro-data.php")
  Call<List<HoneyAndJam>> getHoneyAndJamFragment();

  @POST("get-paste-sauce-frag-pro-data.php")
  Call<List<PasteAndSauce>> getPasteAndSauceFragment();

  @POST("get-pickles-frag-pro-data.php")
  Call<List<Pickles>> getPickleseFragment();

  @POST("get-spice-frag-pro-data.php")
  Call<List<Spice>> getSpiceFragment();

  @POST("get-sugar-frag-pro-data.php")
  Call<List<Sugar>> getSugarFragment();

  @POST("get-sweets-frag-pro-data.php")
  Call<List<Sweets>> getSweetsFragment();


}

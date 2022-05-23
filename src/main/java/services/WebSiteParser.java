package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class WebSiteParser {
	
	public List getFullPkm(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient();
		//configur webclient options    
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		//fetching the web page        
		//Getting all pokemon from bulbapedia by region
		HtmlPage page = webClient.getPage(url);
		//fetch all tables in page and begin recursing through them
		List<HtmlTable> items = page.getByXPath("//table");
		List<String> validSpecies = new ArrayList();
		for(HtmlTable table: items) {
			//Create a counter that will hold the cell location of the pokemon header of the table and set it out of bounds
			int p = -1;
			//recurse through each row
			for(HtmlTableRow row : table.getRows()) {
				//Create a counter of what cell we are on
				int i = 0;
				for(HtmlTableCell cell: row.getCells()) {
					//if the cell's text is pokemon then save the value of 'i' to 'p', else if 'i' is equal to 'p' select that text then increment 'i'
					if(cell.asNormalizedText().equals("Pokémon"))
						p=i;
					else if(i == p) {
						if(!validSpecies.contains(cell.asNormalizedText())) {
							
							validSpecies.add(cell.asNormalizedText());
						}
					}
					i++;
				}

			}
		}
		webClient.close();
		
		return validSpecies;
	}
	
	public List<String> getSmogonPkm(List validSpecies) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient();
		//configuring options    
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		//Pokemon form generation to be used with the bulbapedia species gen: TODO Filter out -Max, -Gmax, -Mega, and -Hitsui
		HtmlPage smogonPage = webClient.getPage("https://github.com/smogon/pokemon-showdown/blob/master/data/pokedex.ts");

		List<DomNode> pokemonList = smogonPage.getByXPath("//span[@class='pl-s']");
		List<String> finalPokemon = new ArrayList();
		String[] invalidTerms = {"Mega","Gmax","Eternamax","Ultra","Busted","Totem","Blade","School","Primal","Starter","Hangry",
				"Gulping","Gorging","Sunshine","Neutral","Noice","Antique","Cosplay","Original","Ruby","Matcha","Mint","Lemon","Salted","Rainbow"
				,"Caramel"};
		for(DomNode node: pokemonList) {
			String temp = node.asNormalizedText().replace("\"", "");
			if(temp.contains("u2019")) {
				temp = temp.replace("u2019", "'");
				temp = temp.replace("\\", "");
				System.out.println(temp);
				
			}
			String[] lst = temp.split("-");
			if (temp.contains("-")&&!temp.contains("Zen")&&!temp.contains("Gmax")&&!ArrayUtils.contains(invalidTerms,lst[1])) {

				if(validSpecies.contains(lst[0])||validSpecies.contains(temp)) {
					if(!finalPokemon.contains(temp))
						finalPokemon.add(temp);
				}
			}
			else if(validSpecies.contains(temp)) {
				if(!finalPokemon.contains(temp))
					finalPokemon.add(temp);
			}
		}
		webClient.close();
		return finalPokemon;
	}
	
	public List getSmogonItems() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient();
		//configuring options    
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		HtmlPage smogonItems = webClient.getPage("https://github.com/smogon/pokemon-showdown/blob/master/data/items.ts");
		List<HtmlTable> itemTable = smogonItems.getByXPath("//table");
		List<String> itemNames = new ArrayList();

		for(HtmlTable table:itemTable) {
			for(HtmlTableRow row: table.getRows()) {
				for(HtmlTableCell cell: row.getCells()) {
					if(cell.asNormalizedText().contains("name:")&&!cell.asNormalizedText().contains("TR")&&!cell.asNormalizedText().contains("Choice")
							&&!cell.asNormalizedText().contains("Life"))
					{
						String[] itm = cell.asNormalizedText().split("\"");
						itemNames.add(itm[1]);
					}
					if(cell.asNormalizedText().contains("Past")||cell.asNormalizedText().contains("Crucibellite")||cell.asNormalizedText().contains("Vile Vial")){
						itemNames.remove(itemNames.size()-1);
					}
				}
			}
		}
		webClient.close();
		return itemNames;
	}
	
}

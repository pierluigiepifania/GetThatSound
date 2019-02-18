package test.model;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import it.model.BranoBean;
import it.model.BranoManager;

class BranoManagerTest {

	private static BranoManager branoManager;
	Gson gson = new Gson();

	static {
		branoManager = new BranoManager();
	}

	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@SuppressWarnings("static-access")
	@Test
	void TestGetHomeBrani() throws JsonSyntaxException, SQLException {
		BranoBean[] bb_list = gson.fromJson(branoManager.getHomeBrani(), BranoBean[].class);
		Assert.assertNotNull(bb_list);
		Assert.assertEquals("Dum Surfer", bb_list[0].getTitolo());
		Assert.assertEquals("Unluck", bb_list[1].getTitolo());
		Assert.assertEquals("I Shot The Sheriff", bb_list[2].getTitolo());
		Assert.assertEquals("Bubble", bb_list[3].getTitolo());

	}
	@SuppressWarnings("static-access")
	@Test
	void TestCercaBrano() throws JsonSyntaxException, SQLException {
		BranoBean[] bb_list = gson.fromJson(branoManager.cercaBrano("unluck"), BranoBean[].class);

		Assert.assertNotNull(bb_list);
		Assert.assertEquals("Unluck", bb_list[0].getTitolo());
		Assert.assertEquals("James Blake", bb_list[0].getArtista());
		Assert.assertEquals("James Blake", bb_list[0].getAlbum());		
	}
	@SuppressWarnings("static-access")
	@Test
	void TestGetBraniUtente() throws JsonSyntaxException, SQLException {
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniUtente(1), BranoBean[].class);

		Assert.assertNotNull(bb_list);
		Assert.assertEquals("Dum Surfer", bb_list[0].getTitolo());
		Assert.assertEquals("Unluck", bb_list[1].getTitolo());		
	}

	@SuppressWarnings("static-access")
	@Test
	void TestGetBranoById() throws JsonSyntaxException, SQLException {
		BranoBean bb = branoManager.getBranoById(1);

		Assert.assertNotNull(bb);
		Assert.assertEquals("Dum Surfer", bb.getTitolo());
		Assert.assertEquals("King Krule", bb.getArtista());	
		Assert.assertEquals("The OOZ", bb.getAlbum());	
	}
	@SuppressWarnings("static-access")
	@Test
	void TestGetBraniSalvati() throws JsonSyntaxException, SQLException {
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniSalvati(1), BranoBean[].class);

		Assert.assertNotNull(bb_list);
		Assert.assertEquals("Dum Surfer", bb_list[0].getTitolo());
		Assert.assertEquals("King Krule", bb_list[0].getArtista());	
		Assert.assertEquals("The OOZ", bb_list[0].getAlbum());	
	}
	@SuppressWarnings("static-access")
	@Test
	void TestGetBraniStallo() throws JsonSyntaxException, SQLException {
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniStallo(), BranoBean[].class);

		Assert.assertNotNull(bb_list);
		Assert.assertEquals("Parallel Jalebi", bb_list[0].getTitolo());
		Assert.assertEquals("Grimes", bb_list[1].getArtista());	
	}
	@SuppressWarnings("static-access")
	@Test
	void TestUploadBranoStallo() throws JsonSyntaxException, SQLException {
		BranoBean bb = new BranoBean(0, "TitoloTest", "ArtistaTest", "AlbumTest", "PathTest", 1);
		branoManager.uploadBranoInStallo(bb);
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniStallo(), BranoBean[].class);		
		Assert.assertEquals("TitoloTest", bb_list[2].getTitolo());	
	}
	@SuppressWarnings("static-access")
	@Test
	void TestEliminaBranoSalvato() throws JsonSyntaxException, SQLException {
		branoManager.eliminaBranoSalvato(1,1);
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniSalvati(1), BranoBean[].class);
		Assert.assertNull(bb_list);	
	}
	@SuppressWarnings("static-access")
	@Test
	void TestCheckSalvato() throws JsonSyntaxException, SQLException {
		boolean result = branoManager.salvaBrano(3,1);
		Assert.assertEquals(result, true);
		if(result) {
			BranoBean[] bb_list = gson.fromJson(branoManager.getBraniSalvati(1), BranoBean[].class);
			Assert.assertNotNull(bb_list);
			Assert.assertEquals("I Shot The Sheriff", bb_list[1].getTitolo());	
		}
	}
	@SuppressWarnings("static-access")
	@Test
	void TestApprovaBrano() throws JsonSyntaxException, SQLException {
		BranoBean bb = new BranoBean(0, "TitoloTest", "ArtistaTest", "AlbumTest", "PathTest", 1);
		branoManager.uploadBranoInStallo(bb);
		branoManager.approvaBrano(3);
		BranoBean bb1 = branoManager.getBranoById(5);
		Assert.assertEquals("TitoloTest", bb1.getTitolo());
	}
	@SuppressWarnings("static-access")
	@Test
	void TestModificaBranoStallo() throws JsonSyntaxException, SQLException {
		int id = 0;
		BranoBean bb = new BranoBean("TitoloTest", "ArtistaTest", "AlbumTest", "PathTest", 1);
		branoManager.uploadBranoInStallo(bb);
		BranoBean[] bb_list = gson.fromJson(branoManager.getBraniStallo(), BranoBean[].class);
		for(BranoBean temp : bb_list) {
			if(temp.getTitolo().equals("TitoloTest")) id = temp.getId();
		}
		BranoBean bb_mod = new BranoBean(id, "TitoloTest_mod", "ArtistaTest_mod", "AlbumTest_mod", "PathTest_mod", 1);
		branoManager.modificaBranoStallo(bb_mod);
		BranoBean[] bb_list_mod = gson.fromJson(branoManager.getBraniStallo(), BranoBean[].class);
		for(BranoBean temp : bb_list_mod) {
			if(temp.getTitolo().equals("TitoloTest_mod")) {
				Assert.assertEquals("ArtistaTest_mod", temp.getArtista());
				Assert.assertEquals("AlbumTest_mod", temp.getAlbum());
				Assert.assertEquals("PathTest_mod", temp.getPath());
			}
		}
	}
	@SuppressWarnings("static-access")
	@Test
	void TestModificaBrano() throws JsonSyntaxException, SQLException {
		BranoBean bb = new BranoBean("TitoloTest", "ArtistaTest", "AlbumTest", "PathTest", 1);
		branoManager.uploadBranoInStallo(bb);
		branoManager.approvaBrano(3);
		BranoBean bb_mod = new BranoBean(3, "TitoloTest mod", "ArtistaTest mod", "AlbumTest mod", "PathTest mod", 1);
		branoManager.modificaBrano(bb_mod);
		BranoBean bb1 = branoManager.getBranoById(3);
		Assert.assertEquals("TitoloTest mod", bb1.getTitolo());
	}
	@SuppressWarnings("static-access")
	@Test
	void TestSalvaBrano() throws JsonSyntaxException, SQLException {
		boolean result = branoManager.salvaBrano(3,1);
		Assert.assertEquals(result, true);
		if(result) {
			BranoBean[] bb_list = gson.fromJson(branoManager.getBraniSalvati(1), BranoBean[].class);
			Assert.assertNotNull(bb_list);
			Assert.assertEquals("I Shot The Sheriff", bb_list[1].getTitolo());	
		}
	}

}
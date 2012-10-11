import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sokoban.model.Box;
import sokoban.model.Player;
import sokoban.model.Stockpile;

import sokoban.model.Warehouse.Movement;
import sokoban.model.Warehouse;


public class TestWarehouse4 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	Integer[][] emptyRowMap = {{Stockpile.Empty, Stockpile.Empty,  Stockpile.Empty}};

	Integer[][] emptyColumnMap = {{Stockpile.Empty}, {Stockpile.Empty},  {Stockpile.Empty}};
	
	/* X . X
	   S P .
	   X S X
	 */
	private Warehouse createBaseWarehouse() {
		Integer[][] map = { {Warehouse.Block, Warehouse.Empty, Warehouse.Block}, 
				{Warehouse.Storage, Warehouse.Empty, Warehouse.Empty},
				{Warehouse.Block, Warehouse.Storage, Warehouse.Block} };

		
		whs = new Warehouse(map);
		p = new Player(1,1);
		
		whs.setPlayer(p);
		
		return whs ;
	}
	
	@Test
	public void testCreateWarehouseWithBlocksAndEmptyAndStorageCells() {
		createBaseWarehouse();
		
		assertNotNull(whs.getMap());
	}

	
	@Test
	public void testAddTwoBoxesIntoWarehouse() {
		/* X . X
		   S . S
		   X . X
		 */
		createBaseWarehouse();
		
		Box b1 = new Box(1, 0);
		whs.addBox(b1);
		
		Box b2 = new Box(2, 1);
		whs.addBox(b2);
		

		assertEquals(2, whs.boxs.size());
	}

	Warehouse whs;
	Player p;
	
	@Test
	public void testMovePlayerToAEmptyCellInTheLeft() {
		createBaseWarehouse();
		
		whs.movePlayer(Movement.Left);
		
		assertEquals(0, p.getX());
		assertEquals(1, p.getY());	
	}

	
	@Test
	public void testMovePlayerToAEmptyCellInTheRight() {
		createBaseWarehouse();
		
		whs.movePlayer(Movement.Right);
		
		assertEquals(2, p.getX());
		assertEquals(1, p.getY());	
	}

	@Test
	public void testMovePlayerToAEmptyCellUp() {
		createBaseWarehouse();
		
		whs.movePlayer(Movement.Up);
		
		assertEquals(1, p.getX());
		assertEquals(0, p.getY());	
	}

	@Test
	public void testMovePlayerToAEmptyCellDown() {
		createBaseWarehouse();
		
		whs.movePlayer(Movement.Down);
		
		assertEquals(1, p.getX());
		assertEquals(2, p.getY());	
	}

	@Test
	public void testMovePlayerRightToABlock_NothingChanges() {
		createBaseWarehouse();
		p = new Player(1,0);
		
		whs.setPlayer(p);
		
		whs.movePlayer(Movement.Right);
		
		assertEquals(1, p.getX());
		assertEquals(0, p.getY());	
	}

	@Test
	public void testMovePlayerLeftToABlock_NothingChanges() {
		createBaseWarehouse();
		p = new Player(1,0);
		
		whs.setPlayer(p);
		
		whs.movePlayer(Movement.Left);
		
		assertEquals(1, p.getX());
		assertEquals(0, p.getY());	
	}
	
	@Test
	public void testMovePlayerUpToABlock_NothingChanges() {
		createBaseWarehouse();
		p = new Player(0,1);
		
		whs.setPlayer(p);
		
		whs.movePlayer(Movement.Up);
		
		assertEquals(0, p.getX());
		assertEquals(1, p.getY());	
	}
	
	@Test
	public void testMovePlayerDownToABlock_NothingChanges() {
		createBaseWarehouse();
		p = new Player(0,1);
		
		whs.setPlayer(p);
		
		whs.movePlayer(Movement.Down);
		
		assertEquals(0, p.getX());
		assertEquals(1, p.getY());	
	}

	@Test
	public void testMovePlayerRightPushingABox() {
		Warehouse whs = new Warehouse(emptyRowMap);
	
		Player p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b = new Box(1, 0);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Right);

		assertTrue(p.isIn(1,0));
		assertTrue(b.isIn(2,0));
	}

	@Test
	public void testMovePlayerDownPushingABox() {
		Warehouse whs = new Warehouse(emptyColumnMap);
	
		Player p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b = new Box(0, 1);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Down);

		assertTrue(p.isIn(0,1));
		assertTrue(b.isIn(0,2));
	}

	@Test
	public void testMovePlayerUpPushingABox() {
		Warehouse whs = new Warehouse(emptyColumnMap);
	
		Player p = new Player(0,2);
		whs.setPlayer(p);
		
		Box b = new Box(0, 1);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Up);

		assertTrue(p.isIn(0,1));
		assertTrue(b.isIn(0,0));
	}

	@Test
	public void testMovePlayerLeftPushingABox() {
		Warehouse whs = new Warehouse(emptyRowMap);
	
		Player p = new Player(2,0);
		whs.setPlayer(p);
		
		Box b = new Box(1, 0);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Left);

		assertTrue(p.isIn(1,0));
		assertTrue(b.isIn(0,0));
	}


	@Test
	public void testMovePlayerRightPushingABoxWithTwoboxesIntheWarehouse() {
		Integer[][] map = 
			{{Stockpile.Empty, Stockpile.Empty, Stockpile.Empty,  Stockpile.Empty}};
		Warehouse whs = new Warehouse(map);
	
		Player p = new Player(1,0);
		whs.setPlayer(p);
		
		Box b1 = new Box(0, 0);
		whs.addBox(b1);
		
		Box b2 = new Box(2, 0);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Right);

		assertTrue(p.isIn(2,0));
		assertTrue(b1.isIn(0,0));
		assertTrue(b2.isIn(3,0));
	}

	@Test
	public void testMovePlayerLeftPushingABoxWithTwoboxesIntheWarehouse() {
		Integer[][] map = 
			{{Stockpile.Empty, Stockpile.Empty, Stockpile.Empty,  Stockpile.Empty}};
		Warehouse whs = new Warehouse(map);
	
		Player p = new Player(2,0);
		whs.setPlayer(p);
		
		Box b1 = new Box(1, 0);
		whs.addBox(b1);
		
		Box b2 = new Box(3, 0);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Left);

		assertTrue(p.isIn(1,0));
		assertTrue(b1.isIn(0,0));
		assertTrue(b2.isIn(3,0));
	}


	@Test
	public void testMovePlayerUpPushingABoxWithTwoboxesIntheWarehouse() {
		Integer[][] map = 
			{{Stockpile.Empty}, {Stockpile.Empty}, {Stockpile.Empty},  {Stockpile.Empty}};
		Warehouse whs = new Warehouse(map);
	
		Player p = new Player(0,2);
		whs.setPlayer(p);
		
		Box b1 = new Box(0,1);
		whs.addBox(b1);
		
		Box b2 = new Box(0,3);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Up);

		assertTrue(p.isIn(0,1));
		assertTrue(b1.isIn(0,0));
		assertTrue(b2.isIn(0, 3));
	}

	@Test
	public void testMovePlayerDownPushingABoxWithTwoboxesIntheWarehouse() {
		Integer[][] map = 
			{{Stockpile.Empty}, {Stockpile.Empty}, {Stockpile.Empty},  {Stockpile.Empty}};
		Warehouse whs = new Warehouse(map);
	
		Player p = new Player(0,1);
		whs.setPlayer(p);
		
		Box b1 = new Box(0,0);
		whs.addBox(b1);
		
		Box b2 = new Box(0,2);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Down);

		assertTrue(p.isIn(0,2));
		assertTrue(b1.isIn(0,0));
		assertTrue(b2.isIn(0, 3));
	}

	
	@Test
	public void testMovePlayerRightPushingABoxNextToABlock() {
		Integer[][] map = 
			{{Stockpile.Empty, Stockpile.Empty, Stockpile.Block}};
		Warehouse whs = new Warehouse(map);
	
		Player p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b = new Box(1, 0);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Right);

		assertTrue(p.isIn(0,0));
		assertTrue(b.isIn(1,0));
	}

	@Test
	public void testMovePlayerLeftPushingABoxNextToABlock() {
		Integer[][] map = 
			{{Stockpile.Block, Stockpile.Empty, Stockpile.Empty}};
		whs = new Warehouse(map);
	
		p = new Player(2,0);
		whs.setPlayer(p);
		
		Box b = new Box(1, 0);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Left);

		assertTrue(p.isIn(2,0));
		assertTrue(b.isIn(1,0));
	}

	
	@Test
	public void testMovePlayerUpPushingABoxNextToABlock() {
		Integer[][] map = 
			{{Stockpile.Block}, {Stockpile.Empty}, {Stockpile.Empty}};
		whs = new Warehouse(map);
	
		p = new Player(0,2);
		whs.setPlayer(p);
		
		Box b = new Box(0,1);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Up);

		assertTrue(p.isIn(0,2));
		assertTrue(b.isIn(0,1));
	}

	@Test
	public void testMovePlayerDownPushingABoxNextToABlock() {
		Integer[][] map = 
			{{Stockpile.Empty}, {Stockpile.Empty}, {Stockpile.Block}};
		whs = new Warehouse(map);
	
		p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b = new Box(0,1);
		whs.addBox(b);
		
		whs.movePlayer(Movement.Down);

		assertTrue(p.isIn(0,0));
		assertTrue(b.isIn(0,1));
	}


	@Test
	public void testMovePlayerRightPushingABoxNextToAnotherBox() {
		whs = new Warehouse(this.emptyRowMap);
	
		Player p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b1 = new Box(1, 0);
		whs.addBox(b1);
	
		Box b2 = new Box(2, 0);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Right);

		assertTrue(p.isIn(0,0));
		assertTrue(b1.isIn(1,0));
		assertTrue(b2.isIn(2,0));
	}


	
	@Test
	public void testMovePlayerLeftPushingABoxNextToAnotherBox() {
		whs = new Warehouse(this.emptyRowMap);
	
		Player p = new Player(2,0);
		whs.setPlayer(p);
		
		Box b1 = new Box(1, 0);
		whs.addBox(b1);
	
		Box b2 = new Box(0, 0);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Left);

		assertTrue(p.isIn(2,0));
		assertTrue(b1.isIn(1,0));
		assertTrue(b2.isIn(0,0));
	}

	
	@Test
	public void testMovePlayerUpPushingABoxNextToAnotherBox() {
		whs = new Warehouse(this.emptyColumnMap);
	
		Player p = new Player(0,2);
		whs.setPlayer(p);
		
		Box b1 = new Box(0,1);
		whs.addBox(b1);
	
		Box b2 = new Box(0, 0);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Up);

		assertTrue(p.isIn(0, 2));
		assertTrue(b1.isIn(0, 1));
		assertTrue(b2.isIn(0,0));
	}

	
	@Test
	public void testMovePlayerDownPushingABoxNextToAnotherBox() {
		whs = new Warehouse(this.emptyColumnMap);
	
		Player p = new Player(0,0);
		whs.setPlayer(p);
		
		Box b1 = new Box(0,1);
		whs.addBox(b1);
	
		Box b2 = new Box(0, 2);
		whs.addBox(b2);
		
		whs.movePlayer(Movement.Down);

		assertTrue(p.isIn(0, 0));
		assertTrue(b1.isIn(0, 1));
		assertTrue(b2.isIn(0,2));
	}

	
	//--------------------------------------
	
	@Test
	public void testGameIsNotOver() {
		this.createBaseWarehouse();
		
		assertFalse(whs.isGameOver());
	}

	
	@Test
	public void testGameIsOver() {
		this.createBaseWarehouse();
		
		Box b1 = new Box(0,1);
		whs.addBox(b1);
	
		Box b2 = new Box(1, 2);
		whs.addBox(b2);
		
		
		assertTrue(whs.isGameOver());
	}

}

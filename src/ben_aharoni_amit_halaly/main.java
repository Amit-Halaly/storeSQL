package ben_aharoni_amit_halaly;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {
	private static StoreMemento backup;

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner input = new Scanner(System.in);
		storeFacade s = storeFacade.getInstance();
		String choose = "0";
		do {
			System.out.println("press:\n 1)  \n " + "2) to add a new product  \n" + " 3) to remove a product \n "
					+ "4) to update a product's amount \n" + " 5) to add an order for a product \n" + " 6)  \n"
					+ " 7) to review a prodact's details \n" + " 8) to review all of the products \n"
					+ " 9) to print a product's orders \n" + " 10)  \n" + " e\\E) to exit  \n ");
			choose = input.nextLine();
			switch (choose) {
			case "1":
				// automaticCreate(s);

				break;

			case "2":
				addNewProduct(s);

				break;

			case "3":
				removeproduct(s);

				break;

			case "4":
				updateProductAmount(s);

				break;

			case "5":
				addOrderToproduct(s);

				break;

			case "6":
				// undo(s);

				break;

			case "7":
				printAllDetailsForSingleProduct(s);

				break;

			case "8":
				printAllProducts(s);

				break;

			case "9":
				printSingleProduct(s);

				break;

			case "10":
				int choice = 0;
				System.out.println("press:\n 1) to backup the program  \n " + "2) to reset the last state   \n");
				choice = input.nextInt();
				switch (choice) {
				case 1:
					backupProgram(s);
					input.nextLine();

					break;

				case 2:
					resetProgram(s);
					input.nextLine();

					break;

				default:
					System.out.println("invalid choice try again");
					input.nextLine();
				}
				break;

			default:
				if (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E")) {
					System.out.println("invalid choice try again");
				}
			}
		} while (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E"));
		System.out.println("bye");
	}

	public static void automaticCreate(storeFacade s) {
		soldthroughwebsiteFactory stwfactory = new soldthroughwebsiteFactory();
		soldinStoreFactory sisfactory = new soldinStoreFactory();
		SoldToWholesellersFactory stfactory = new SoldToWholesellersFactory();
		customer c1 = new customer("amit", "0528569874", 111);
		customer c2 = new customer("ben", "0548965234", 222);
		customer c3 = new customer("daniel", "0542387954", 333);
		products p1 = stwfactory.createsoldthroughwebsite("pants", 20, 50, 10, "israel", "aaa", 10);
		products p2 = stwfactory.createsoldthroughwebsite("gloves", 10, 20, 50, "yeman", "bbb", 15.5);
		products p3 = stwfactory.createsoldthroughwebsite("hats", 20, 40, 30, "usa", "ccc", 20.2);
		products p4 = sisfactory.createsoldinstore("shirt", 50, 150, 20, "ddd", 35);
		products p5 = sisfactory.createsoldinstore("socks", 5, 20, 30, "eee", 50.3);
		products p6 = sisfactory.createsoldinstore("shoes", 100, 200, 10, "fff", 60.5);
		products p7 = stfactory.createSoldToWholesellers("polo", 20, 50, 10, "ggg", 100);
		products p8 = stfactory.createSoldToWholesellers("scarf", 10, 20, 30, "hhh", 6.9);
		products p9 = stfactory.createSoldToWholesellers("underwear", 40, 80, 20, "zzz", 98.99);

		s.addProduct(p1);
		p1.addOrder(c1, 11, 1, eShipmentType.EXPRESS);
		p1.addOrder(c2, 12, 1, eShipmentType.STANDARD);
		p1.addOrder(c3, 13, 1, eShipmentType.EXPRESS);

		s.addProduct(p2);
		p2.addOrder(c1, 21, 1, eShipmentType.EXPRESS);
		p2.addOrder(c2, 22, 1, eShipmentType.STANDARD);
		p2.addOrder(c3, 23, 1, eShipmentType.EXPRESS);

		s.addProduct(p3);
		p3.addOrder(c1, 31, 1, eShipmentType.EXPRESS);
		p3.addOrder(c2, 32, 1, eShipmentType.STANDARD);
		p3.addOrder(c3, 33, 1, eShipmentType.EXPRESS);

		s.addProduct(p4);
		p4.addOrder(c1, 41, 1, eShipmentType.EXPRESS);
		p4.addOrder(c2, 42, 1, eShipmentType.STANDARD);
		p4.addOrder(c3, 43, 1, eShipmentType.EXPRESS);

		s.addProduct(p5);
		p5.addOrder(c1, 51, 1, eShipmentType.EXPRESS);
		p5.addOrder(c2, 52, 1, eShipmentType.STANDARD);
		p5.addOrder(c3, 53, 1, eShipmentType.EXPRESS);

		s.addProduct(p6);
		p6.addOrder(c1, 61, 1, eShipmentType.EXPRESS);
		p6.addOrder(c2, 62, 1, eShipmentType.STANDARD);
		p6.addOrder(c3, 63, 1, eShipmentType.EXPRESS);

		s.addProduct(p7);
		p7.addOrder(c1, 71, 1, eShipmentType.EXPRESS);
		p7.addOrder(c2, 72, 1, eShipmentType.STANDARD);
		p7.addOrder(c3, 73, 1, eShipmentType.EXPRESS);

		s.addProduct(p8);
		p8.addOrder(c1, 81, 1, eShipmentType.EXPRESS);
		p8.addOrder(c2, 82, 1, eShipmentType.STANDARD);
		p8.addOrder(c3, 83, 1, eShipmentType.EXPRESS);

		s.addProduct(p9);
		p9.addOrder(c1, 91, 1, eShipmentType.EXPRESS);
		p9.addOrder(c2, 92, 1, eShipmentType.STANDARD);
		p9.addOrder(c3, 93, 1, eShipmentType.EXPRESS);
		ShippingCompany sc1 = new DHLcomp("DHL", "ben", 0, "052-4444444", 1111);
		s.addShippingCompany(sc1);
		ShippingCompany sc2 = new FDEXcomp("FDEX", "amit", 0, "054-2222222", 2222);
		s.addShippingCompany(sc2);
		System.out.println("auto creation completed");
	}

	public static void addNewProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		int choose = 0;
		String product_name;
		String serialnum;
		int cost_price;
		int selling_price;
		int stock;
		double weight;
		do {
			System.out.println("choose a product type\n");
			System.out.println("press:\n 1)for sold in Store  \n " + "2) for sold through website \n"
					+ " 3)for sold to wholesellers\n ");
			choose = input.nextInt();
			if (choose < 1 || choose > 3) {
				System.out.println("invalid option");
				return;
			}
		} while (choose == 0);
		System.out.println("please enter product name : ");
		product_name = input.next();
		System.out.println("please enter serial code : ");
		serialnum = input.next();
		System.out.println("please enter product cost price : ");
		cost_price = input.nextInt();
		System.out.println("please enter product selling price : ");
		selling_price = input.nextInt();
		System.out.println("please enter product weight : ");
		weight = input.nextDouble();
		System.out.println("please enter the amount of products : ");
		stock = input.nextInt();
		switch (choose) {
		case 1:
			soldinStoreFactory sisfactory = new soldinStoreFactory();
			s.addProduct(
					sisfactory.createsoldinstore(product_name, cost_price, selling_price, stock, serialnum, weight));
			break;

		case 2:
			System.out.println("please enter the dest country : ");
			String dest_country = input.next();
			soldthroughwebsiteFactory stwfactory = new soldthroughwebsiteFactory();
			s.addProduct(stwfactory.createsoldthroughwebsite(product_name, cost_price, selling_price, stock,
					dest_country, serialnum, weight));
			break;

		case 3:
			SoldToWholesellersFactory stfactory = new SoldToWholesellersFactory();
			s.addProduct(stfactory.createSoldToWholesellers(product_name, cost_price, selling_price, stock, serialnum,
					weight));
			break;

		}
	}

	public static void removeproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		while (foundObject == null) {
			System.out.println("\nEnter the serial code of the product you want to change :\n");
			String serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		s.deleteProduct(foundObject);
	}

	public static void updateProductAmount(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		while (foundObject == null) {
			System.out.println("\nEnter the serial code of the product you want to change :\n");
			String serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		System.out.println("\nEnter the new amount :\n");
		s.updateStock(foundObject, input.nextInt());
	}

	public static void addOrderToproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		int amount, i = 0, cid, oid;
		String serial, name, mobile;
		while (foundObject == null) {
			System.out.println("\nEnter the serial number of the product you want to make an order:\n");
			serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		System.out.println("please enter the customer's name:");
		name = input.next();
		System.out.println("please enter the customer's id");
		cid = input.nextInt();
		System.out.println("please enter the mobile:");
		mobile = input.next();
		System.out.println("please enter the order id number:");
		oid = input.nextInt();
		System.out.println("please enter the amount:");
		amount = input.nextInt();
		while (i <= 0 || i > 3) {
			System.out.println("press: ");
			for (eShipmentType type : eShipmentType.values()) {
				System.out.println("(" + (i++) + ") for " + type);
				if (i == 3)
					System.out.println("(" + (i++) + ") for without shipment");
				i = input.nextInt();
			}
			switch (i) {
			case 1:
				foundObject.addOrder(new customer(name, mobile, cid), oid, amount, eShipmentType.EXPRESS);
				System.out.println("order has been created");
				break;

			case 2:
				foundObject.addOrder(new customer(name, mobile, cid), oid, amount, eShipmentType.STANDARD);
				System.out.println("order has been created");
				break;

			default:
				System.out.println("invalid try again");
				break;
			}
		}
		if (i == 3) {
			foundObject.addOrderWithoutShipment(new customer(name, mobile, cid), oid, amount);
		}
		input.nextLine();
	}

	public static void undo(storeFacade s) {
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to undo ");
			return;
		}
		Scanner input = new Scanner(System.in);
		print(s);
		System.out.println("\nEnter the serial code of the product you want to undo the last order for: \n");
		String serial = input.next();
		products product = s.findProductBySerial(serial);
		if (product == null) {
			System.out.println("No product found with the serial : " + serial);
			return;
		}
		/*
		 * if (product.undoOrder()) { return; } else {
		 * System.out.println("There are no orders to undo for this product."); }
		 */
	}

	public static void printAllDetailsForSingleProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		Connection conn = null;
		System.out.println("\nEnter the serial code of the product you want to view :\n");
		products foundObject = null;
		String serial = input.next();
		/*
		 * try { Class.forName("org.postgresql.Driver"); String dbUrl =
		 * "jdbc:postgresql://localhost:5432/college"; conn =
		 * DriverManager.getConnection(dbUrl, "postgres", "159632"); Statement stmt =
		 * conn.createStatement(); String sql =
		 * "SELECT * FROM productstable WHERE pid= '" + serial + "';"; ResultSet rs =
		 * stmt.executeQuery(sql); while (rs.next()) { foundObject.pid =
		 * rs.getString("pid"); foundObject.product_name = rs.getString("productname");
		 * foundObject.cost_price = rs.getInt("costprice"); foundObject.selling_price =
		 * rs.getInt("sellingprice"); foundObject.weight = rs.getFloat("weight");
		 * foundObject.stock = rs.getInt("stock"); ((soldThroughWebsite)
		 * foundObject).setDest_country(rs.getString("destcountry")); }
		 * stmt.closeOnCompletion(); } catch (Exception ex) {
		 * System.out.println("SQLException: " + ex.getMessage()); } if (foundObject ==
		 * null) { System.out.println("invalid input"); return; }
		 * System.out.println(foundObject); int i = 1, profit = 0, profit1 = 0;
		 * System.out.println("\nthe orders for " + foundObject.getProduct_name() +
		 * ":\n");/*
		 * 
		 * 
		 * /* for (orders order : foundObject.getOrderList()) { if (foundObject
		 * instanceof soldThroughWebsite) { profit1 = foundObject.selling_price *
		 * order.getQuantity() - foundObject.cost_price * order.getQuantity();
		 * System.out.println("\n" + (i++) + ")" + order + "the profit for this order :"
		 * + profit1 + "$\n"); System.out.println(s.findCheapestShippingOption(order));
		 * profit += foundObject.selling_price * order.getQuantity() -
		 * foundObject.cost_price * order.getQuantity(); } else { profit1 =
		 * foundObject.selling_price * order.getQuantity() - foundObject.cost_price *
		 * order.getQuantity(); System.out.println("\n" + (i++) + ")" + order +
		 * "the profit for this order: " + profit1 * 4 + "₪\n"); profit +=
		 * foundObject.selling_price * order.getQuantity() - foundObject.cost_price *
		 * order.getQuantity(); if (foundObject instanceof soldinStore) { ((soldinStore)
		 * foundObject).invoiceFormatToAcountant(foundObject, order.getQuantity());
		 * ((soldinStore) foundObject).invoiceFormatToCustomer(foundObject,
		 * order.getCustomer(), order.getQuantity()); } else ((SoldToWholesellers)
		 * foundObject).invoiceFormatToAcountant(foundObject, order.getQuantity()); } }
		 */
		/*
		 * if (foundObject instanceof soldThroughWebsite) {
		 * System.out.println("the total profit from this product is: " + profit + "$");
		 * System.out.println("\n--------------------\n"); } else {
		 * System.out.println("the total profit from this product is: " + profit * 4 +
		 * "₪");
		 */

		try {
			// Load PostgreSQL JDBC Driver
			Class.forName("org.postgresql.Driver");

			// Connect to the database
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();

			// Fetch the product details
			String sql = "SELECT * FROM productstable WHERE pid= '" + serial + "';";
			ResultSet rs = stmt.executeQuery(sql);
			String productType = "";

			if (rs.next()) {
				foundObject.pid = rs.getString("pid");
				foundObject.product_name = rs.getString("productname");
				foundObject.cost_price = rs.getInt("costprice");
				foundObject.selling_price = rs.getInt("sellingprice");
				foundObject.weight = rs.getFloat("weight");
				foundObject.stock = rs.getInt("stock");

				// Determine the product type
				String typeQuery = "SELECT 'Website' AS type FROM SoldThroughWebsiteProductsTable WHERE PID = '"
						+ serial + "' " + "UNION "
						+ "SELECT 'Store' AS type FROM SoldInStoreProductsTable WHERE PID = '" + serial + "' "
						+ "UNION " + "SELECT 'Wholeseller' AS type FROM SoldToWholesellersProductsTable WHERE PID = '"
						+ serial + "'";
				ResultSet typeResult = stmt.executeQuery(typeQuery);

				if (typeResult.next()) {
					productType = typeResult.getString("type");
				}

				// Fetch related orders and customer information
				String ordersQuery = "SELECT ot.OID, ot.quantity, ot.shipmentType, ct.CustomerName, ct.Mobile "
						+ "FROM OrdersTable ot " + "JOIN OrderCustomerTable oct ON ot.OID = oct.OID "
						+ "JOIN CustomerTable ct ON oct.CID = ct.CID "
						+ "WHERE ot.OID IN (SELECT OID FROM OrderCustomerTable WHERE CID = (SELECT CID FROM OrderCustomerTable WHERE PID = '"
						+ serial + "'))";
				ResultSet ordersResult = stmt.executeQuery(ordersQuery);
				int i = 1, totalProfit = 0;
				while (ordersResult.next()) {
					int quantity = ordersResult.getInt("quantity");
					String shipmentType = ordersResult.getString("shipmentType");
					String customerName = ordersResult.getString("CustomerName");
					String mobile = ordersResult.getString("Mobile");

					double profit = (foundObject.selling_price - foundObject.cost_price) * quantity;

					if (productType.equals("Website")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: $" + profit);
					} else if (productType.equals("Store")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: ₪" + profit * 4);

						// Invoice formatting for store sales
						System.out.println("Store invoice formatted for accountant and customer.");
					} else if (productType.equals("Wholeseller")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: $" + profit);

						// Invoice formatting for wholeseller sales
						System.out.println("Wholeseller invoice formatted for accountant.");
					}

					totalProfit += profit;
				}

				System.out.println("\nTotal Profit for Product '" + foundObject.product_name + "' (ID: " + serial
						+ "): $" + totalProfit);
			} else {
				System.out.println("Product not found with the provided serial code.");
			}

			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void printAllProducts(storeFacade s) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM productstable;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("PID: "+rs.getString("pid") + "\nproduct name: " + rs.getString("productname") + "\ncost price: "
						+ rs.getInt("costprice") + "\nselling price: " + rs.getInt("sellingprice") + "\nwighet: " + rs.getFloat("weight")
						+"\nstock: " +rs.getInt("stock")+"\n\n");
			}
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public static void printSingleProduct(storeFacade s) {
		Connection conn = null;
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter the serial code of the product you want to view :\n");
		String serial = input.next();
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM productstable WHERE pid= '" + serial + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("pid") + "- " + rs.getString("productname") + "- "
						+ rs.getInt("costprice") + "- " + rs.getInt("sellingprice") + "- " + rs.getFloat("weight")
						+ rs.getInt("stock") + "- " + rs.getString("destcountry"));
			}
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}

	}

	public static void backupProgram(storeFacade s) throws CloneNotSupportedException {
		// backup = s.saveMem();
		System.out.println("the current state has been backed up");
	}

	public static void resetProgram(storeFacade s) {
		if (backup != null) {
			s.resetMem(backup);
			System.out.println("the previous state has been restored");
		} else {
			System.out.println("No backup has been made , please ensure to backup the program before the reset.");
		}
	}

	public static void print(storeFacade s) {

		Iterator<products> it = s.getProductIterator();
		while (it.hasNext()) {
			products element = it.next();
			System.out.println(element);
			System.out.println("-----------------------");

		}
	}
}

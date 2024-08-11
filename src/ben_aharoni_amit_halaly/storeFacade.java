package ben_aharoni_amit_halaly;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

public class storeFacade {
	private final int DOLLAR_RATE = 4;
	private int Sid;
	private String storeName;
	private static storeFacade instance = null;
	private Caretaker caretaker = new Caretaker();
	private TreeSet<products> product_list = new TreeSet<products>();
	private ArrayList<ShippingCompany> ShippingCompany_list = new ArrayList<>();

	private storeFacade(int sid, String StoreName) {
		setSid(sid);
		setStoreName(StoreName);

	}

	public static storeFacade getInstance() {
		if (instance == null) {
			instance = new storeFacade(1, "GAB");
		}
		return instance;
	}

	public TreeSet<products> getProduct_list() {
		return product_list;
	}

	void addProduct(products product) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO productsTable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
					+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
					+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		if (product instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "INSERT INTO soldthroughwebsiteproductstable (pid, productname, costprice,sellingprice, weight, stock,destcountry) VALUES ('"
						+ product.pid + "' ,'" + product.product_name + " '," + product.cost_price + " ,"
						+ product.selling_price + " ," + product.weight + "," + product.stock + ", '"
						+ ((soldThroughWebsite) product).getDest_country() + "');";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (product instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql ="INSERT INTO soldinstoreproductstable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
							+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
							+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql ="INSERT INTO soldtowholesellersproductstable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
							+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
							+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO storeproducttable (pid , sid)  VALUES ('" + product.pid + "', " + this.Sid + ");";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public void addShippingCompany(ShippingCompany shippingCompany) {
		ShippingCompany_list.add(shippingCompany);
	}

	public ArrayList<ShippingCompany> getShippingCompaniesList() {
		return ShippingCompany_list;
	}

	public void deleteProduct(products foundObject) {
		Connection conn = null;
		if (foundObject instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "DELETE FROM soldthroughwebsiteproductstable WHERE pid='" + foundObject.pid
						+ "'; DELETE FROM productsTable WHERE pid='" + foundObject.pid + "';";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (foundObject instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "DELETE FROM soldinstoreproductstable WHERE pid='" + foundObject.pid
							+ "'; DELETE FROM productsTable WHERE pid='" + foundObject.pid + "';";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "DELETE FROM soldtowholesellersproductstable WHERE pid='" + foundObject.pid
							+ "'; DELETE FROM productsTable WHERE pid='" + foundObject.pid + "';";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM storeproducttable WHERE pid='" + foundObject.pid + "';";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public void updateStock(products foundObject, int amount) {
		Connection conn = null;
		if (foundObject instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "UPDATE productstable\r\n" + "SET stock= " + amount + "\r\n" + "WHERE pid='"
						+ foundObject.pid + "';\r\n " + "UPDATE soldthroughwebsiteproductstable\r\n" + "SET stock= "
						+ amount + "\r\n" + "WHERE pid='" + foundObject.pid + "'; ";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (foundObject instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "UPDATE productstable\r\n" + "SET stock= " + amount + "\r\n" + "WHERE pid='"
							+ foundObject.pid + "';\r\n " + "UPDATE soldinstoreproductstable\r\n" + "SET stock= "
							+ amount + "\r\n" + "WHERE pid='" + foundObject.pid + "'; ";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "UPDATE productstable\r\n" + "SET stock= " + amount + "\r\n" + "WHERE pid='"
							+ foundObject.pid + "';\r\n " + "UPDATE soldtowholesellersproductstable\r\n" + "SET stock= "
							+ amount + "\r\n" + "WHERE pid='" + foundObject.pid + "'; ";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}
	}

	public Iterator<products> getProductIterator() {
		return product_list.iterator();
	}

	public products findProductBySerial(String serialId) {
		for (products product : product_list) {
			if (product.getPid().equals(serialId)) {
				return product;
			}
		}
		return null;
	}

	public void resetMem(StoreMemento memento) {
		this.product_list = new TreeSet<>(memento.getState());
	}

	public ShippingCompany findCheapestShippingOption(orders order) {
		ShippingCompany cheapest = null;
		double lowestFee = Double.MAX_VALUE;
		for (ShippingCompany company : ShippingCompany_list) {
			double fee = company.calculateShippingFee(order);
			if (fee < lowestFee) {
				lowestFee = fee;
				cheapest = company;
			}
		}
		cheapest.setShippingFee(lowestFee);
		return cheapest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product_list);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		storeFacade other = (storeFacade) obj;
		return Objects.equals(product_list, other.product_list);
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public products findProducts(String pid) {
		Connection conn = null;
		int flag = 0;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldinstoreproductstable WHERE pid='" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.getString(pid) == null)
				flag = 1;
			if (flag == 0) {
				stmt.closeOnCompletion();
				soldinStoreFactory sf = new soldinStoreFactory();
				return sf.createsoldinstore(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("pid"), rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldtowholesellersproductstable WHERE pid='" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.getString(pid) == null)
				flag = 1;
			if (flag == 0) {
				stmt.closeOnCompletion();
				SoldToWholesellersFactory sf = new SoldToWholesellersFactory();
				return sf.createSoldToWholesellers(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("pid"), rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldthroughwebsiteproductstable WHERE pid='" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.getString(pid) == null)
				flag = 1;
			if (flag == 0) {
				stmt.closeOnCompletion();
				soldthroughwebsiteFactory sf = new soldthroughwebsiteFactory();
				return sf.createsoldthroughwebsite(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("destcountry"), rs.getString("pid"),
						rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		return null;
	}

}

package ben_aharoni_amit_halaly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class products implements Comparable<products>, Cloneable {
	protected String product_name;
	protected String pid;
	protected int cost_price;
	protected int selling_price;
	protected int stock;
	protected double weight;
	protected ArrayList<orders> order_list = new ArrayList<>();

	public products(String product_name, int cost_price, int selling_price, int stock, String pid, double weight) {
		this.setProduct_name(product_name);
		this.setCost_price(cost_price);
		this.setSelling_price(selling_price);
		this.setStock(stock);
		this.setPid(pid);
		this.setWeight(weight);
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ArrayList<orders> getOrderList() {
		return order_list;
	}

	public Iterator<orders> getOrderIterator() {
		return order_list.iterator();
	}

	void addOrder(customer customer, int oid, int quantity, String serial, eShipmentType type) {
		if (quantity > this.stock) {
			System.out.println("Not enough stock available for this product.");
			return;
		}
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO customertable (cid, customername, mobile) VALUES (" + customer.getCid() + " , '"
					+ customer.getName() + "' , '" + customer.getMobile() + "')";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			int newStock = this.stock - quantity;
			Statement stmt = conn.createStatement();
			String sql = "UPDATE productstable\r\n" + "SET stock= " + newStock + "\r\n" + "WHERE pid='" + this.pid + "';";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO orderstable (oid, quantity, shipmenttype) VALUES (" + oid + " , " + quantity
					+ " , '" + type + "')";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	void addOrderWithoutShipment(customer customer, int oid, int quantity, String serial) {
		if (quantity > this.stock) {
			System.out.println("Not enough stock available for this product.");
		}
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO customertable (cid, customername, mobile) VALUES (" + customer.getCid() + " , '"
					+ customer.getName() + "' , '" + customer.getMobile() + "')";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			int newStock = this.stock - quantity;
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Customers\r\n" + "SET stock= " + newStock + "\r\n" + "WHERE pid='" + this.pid + "';";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO orderstable (oid, quantity, shipmenttype) VALUES (" + oid + " , " + quantity
					+ " , 'Without')";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public boolean undoOrder() {
		if (order_list.isEmpty()) {
			return false;
		}
		Iterator<orders> iterator = order_list.iterator();
		orders recentOrder = null;

		while (iterator.hasNext()) {
			recentOrder = iterator.next();
		}

		if (recentOrder != null) {
			iterator.remove();
			System.out.println("Order " + recentOrder.getSerial() + " has been cancelled by the store");
			stock += recentOrder.getQuantity();
			return true;
		}
		return false;
	}

	public products pclone() {
		try {
			products cloned = (products) super.clone();
			cloned.order_list = new ArrayList<orders>();
			for (orders order : this.order_list) {
				cloned.order_list.add(order);
			}
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(pid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		products other = (products) obj;
		return Objects.equals(pid, other.pid);
	}

	@Override
	public int compareTo(products p) {
		return this.getPid().compareTo(p.getPid());
	}

	@Override
	public String toString() {

		return "Product Name: " + product_name + "\nProduct serial: " + pid + "\nCurrent Stock: " + stock + "\nweight: "
				+ weight + "\nProduct Type: " + this.getClass().getSimpleName();
	}

}

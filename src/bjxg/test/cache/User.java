package bjxg.test.cache;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5690031328277117114L;
	public User(int age,String name){
		this.age=age;
		this.name=name;
	}
private int age;
private String name;
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "User [age=" + age + ", name=" + name + "]";
}

}

package com.atguigu.zookeeper0529;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public class TestZookeeper {

	/*
	 * server.1=192.168.8.105:2888:3888
server.2=192.168.8.145:2888:3888
server.3=192.168.8.137:2888:3888

	 */
	private String connectString="192.168.8.105:2181,192.168.8.145:2181,192.168.8.137:2181";
	private int sessionTimeout = 2000;
	private ZooKeeper zkClient;

	//
	//@Test
	@Before
	public void init() throws IOException{
		
		zkClient = new ZooKeeper(connectString, sessionTimeout , new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				
//				System.out.println("---------start----------");
//				List<String> children;
//				try {
//					children = zkClient.getChildren("/", true);
//					
//					for (String child : children) {
//						System.out.println(child);
//					}
//					System.out.println("---------end----------");
//				} catch (KeeperException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
	}
	
	// 1 创建节点
	@Test
	public void createNode() throws KeeperException, InterruptedException{
		
		String path = zkClient.create("/atguigu", "dahaigezuishuai".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		System.out.println(path);
		
	}
	
	// 2 获取子节点 并监控节点的变化
	@Test
	public void getDataAndWatch() throws KeeperException, InterruptedException{
		
		List<String> children = zkClient.getChildren("/", true);
		
		for (String child : children) {
			System.out.println(child);
		}
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	// 3 判断节点是否存在
	@Test
	public void exist() throws KeeperException, InterruptedException{
		
		Stat stat = zkClient.exists("/atguigu", false);
		
		System.out.println(stat==null? "not exist":"exist");
	}
}

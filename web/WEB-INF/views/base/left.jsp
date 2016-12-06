<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>

<div id="sidebar" class="sidebar mini-menu">
	<div class="sidebar-menu nav-collapse">
		<div id="sidebar-collapse" class="sidebar-collapse" style="background-color:#FFFFFF;text-align: center;width:100%;height:39px;top:0;left:0;border-color:none;border-top:1px solid #f5f5f5;border-bottom: 1px solid #cdd2d2;">
			<i class="fa fa-bars fa-fw-r" data-icon1="fa fa-bars fa-fw-r" data-icon2="fa fa-bars fa-fw" style="color:#555555;"></i>
		</div>
		<ul>
			<li class="active">
				<a href="<%=path%>/monitor/data"> 
					<i class="fa fa-tachometer fa-fw"></i> 
					<span class="menu-text">数据监控</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-database  fa-fw"></i> 
					<span class="menu-text">数据管理</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-desktop fa-fw"></i> 
					<span class="menu-text">终端管理</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-tasks fa-fw"></i> 
					<span class="menu-text">通道管理</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-users fa-fw"></i> 
					<span class="menu-text">用户管理</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-file-text fa-fw"></i> 
					<span class="menu-text">日志管理</span>
				</a>
			</li>
			<li>
				<a class="" href="<%=path%>/monitor/warn"> 
					<i class="fa fa-warning fa-fw"></i> 
					<span class="menu-text">报警查询</span>
				</a>
			</li>
			<li>
				<a class="" href="javascript:void(0);"> 
					<i class="fa fa-shopping-bag fa-fw"></i> 
					<span class="menu-text">工具/帮助</span>
				</a>
			</li>
		</ul>
	</div>
</div>
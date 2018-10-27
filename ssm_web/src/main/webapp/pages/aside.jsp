<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username" />
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<%--访问系统管理菜单，必须具有ROLE_ADMIN角色权限。--%>
			<security:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li class="treeview">
				<a href="#">
					<i class="fa fa-cogs"></i>
					<span>系统管理</span>
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/user/findByPage"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/role/findByPage"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/permission/findByPage">
							<i class="fa fa-circle-o"></i> 权限管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/pages/syslog-list.jsp"> <i
							class="fa fa-circle-o"></i> 访问日志
					</a></li>
				</ul>
			</li>
			</security:authorize>



			<%--访问基础数据菜单，必须具有ROLE_USER角色权限--%>
			<security:authorize access="hasRole('ROLE_USER')">
			<li class="treeview">
				<a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
				</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/product/findByPage">
							<i class="fa fa-circle-o"></i> 产品管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/order/findByPage">
							<i class="fa fa-circle-o"></i> 订单管理
					</a></li>

				</ul>
			</li>
			</security:authorize>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
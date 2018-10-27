<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добавить новость</title>


<link href="<c:url value="/resources/css/admin_page.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/add_page.css" />"
	rel="stylesheet">

</head> 
<body>

	<header>
		

	</header>
	
	<div class="block">
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath}/">На главную</a></li>
			</ul>
		</div>
	</div>

	<div id="wrapper">

		<div class="news-form">
			<h2 class="title-form">Введите информацию</h2>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/add_news"
				modelAttribute="news">
				<ul>
					<form:input type="hidden" path="id" id="id" />

					<li><form:label path="title">Заголовок:</form:label> <form:input
							class="field-input" path="title" /> <form:errors path="title"
							cssClass="error" /></li>
							 
                     <li class="description-li"><form:label path="content">Текст:</form:label> 
					<form:textarea cols="100" rows="20" class="description-field" 
							path="content" /></li>
					 
				<li class="select-li"><form:label path="category">Категории:</form:label><form:select path="category" items="${categoryList}"
							 itemValue="id" itemLabel="title"
							 /></li>
						<input type="submit" class="btnLogin" value="Добавить"
							tabindex="4"> 
				
				</ul>

			</form:form>


		</div>

	</div>



</body>
</html>
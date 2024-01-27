

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<%@include file="all_component/all_css.jsp"%>
<style>

nav,
.nav-list-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  color: black;
}
.nav-list-container {
  gap: 20px;
}

nav {
  margin: 30px 100px;
  padding: 0px 50px;
  height: 60px;
  border-radius: 10px;
  /* box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); */
}

.nav-list-container button,
.header-buttons button {
  background-color: rgb(59, 130, 236);
  color: white;
  font-weight: bold;
  padding: 6px 20px;
  border-radius: 10px;
}

.header-buttons {
  margin-top: 20px;
  width: 400px;
}
.header-buttons button {
  padding: 10px 25px;
}
.nav-list-container button {
  border: 1px solid rgb(59, 130, 236);
  color: black;
  background-color: white;
}
.login-button button {
  background-color: rgb(59, 130, 236);
  color: white;
}
.container {
  padding: 20px;
  background-color: lightcyan;
  z-index: 100;
  height: 600px;
}

.header-container,
.header-buttons {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-container .item {
  width: 600px;
}
.header-container .item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.header-container {
  padding: 0 100px;
}
.header-title-1 {
  font-size: 50px;
  font-weight: bolder;
  color: black;
}

.header-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1px 100px;
  padding: 80px 20px 50px 20px;
  background-color: white;
  z-index: 0;
  /* border-radius: 10px; */
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);
  position: relative;
}

.header-card .item {
  width: 500px;
}

.header-card .item .title {
  font-size: 40px;
  font-weight: bolder;
  color: black;
}

.header-card .item .title span {
  color: rgb(59, 130, 236);
}
.card {
  color: black;
  font-size: 17px;
}

.card .number {
  color: rgb(59, 130, 236);
  font-weight: bold;
  font-size: 20px;
}

.card.one {
  position: absolute;
  top: 200px;
  left: 300px;
  background-color: white;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
  padding: 1px 20px;
  border-radius: 10px;
}

.card.two {
  position: absolute;
  bottom: 230px;
  left: 400px;
  background-color: white;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
  padding: 1px 20px;
  border-radius: 10px;
}

.categories-container {
  margin: 100px;
}

.child-one {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 50px;
  font-weight: bold;
  margin: 100px 0 50px 0;
}
.job-categories {
  display: grid;
  grid-template-columns: repeat(4, minmax(250px, 1fr));
  gap: 40px;
}
.job-category {
  position: relative;
  height: 200px;
}
.job-category img:hover {
  cursor: pointer;
  transform: scale(1.1);
}
.job-category img {
  filter: brightness(0.5);
  width: 100%;
  height: 100%;
}
.job-category p {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-weight: bold;
  font-size: 20px;
  color: white;
}
.header-buttons.two {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin-top: 100px;
}

.newspaper-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px 100px;
  background-color: lightcyan;
}

.newspaper-title {
  font-size: 50px;
  color: black;
  margin: 20px 0;
}
.newspaper-content {
  font-size: 20px;
  color: gray;
}

.subscription-button-container {
  display: flex;
  justify-content: center;
  align-items: center;
  /* margin-top: 50px; */
}
.newspaper-body button {
  background-color: rgb(59, 130, 236);
  color: white;
  padding: 6px 20px;
  border-radius: 10px;
}
.input-container {
  margin: 60px 0;
}

.input-container input {
  width: 100%;
  padding: 15px;
  border-radius: 10px;
  outline: none;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
<%@include file="all_component/navbar.jsp" %>
    <div class="container">

        <div class="header-container">
            <div class="item">
                <p class="header-title-1">
                    Are you looking for a freelancer or a job?
                </p>
                <p>
                    Hire Great Freelancers, Fast. Spacelance helps you hire elite
                    freelancers at a moment's notice
                </p>

                <div class="header-buttons">
                    <button>Hire a freelancer</button>
                    <button>Apply for a job</button>
                </div>
            </div>

            <div class="item">
                <img src="images/untitled.png" alt="work remotely" />
            </div>
        </div>
    </div>
    <div class="header-card">
        <div class="item">
            <img src="images/female.png" alt="" />
            <div class="card one">
                <p class="number">500+</p>
                <p>freelancers</p>
            </div>
            <div class="card two">
                <p class="number">300+</p>
                <p>jobs per day</p>
            </div>
        </div>
        <div class="item">
            <p class="title">
                Find The Best <span>Freelancers</span> Here
            </p>
            <p class="detail">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut erat
                bibendum ornare urna, cursus eget convallis. Feugiat imperdiet
                posuere justo, ultrices interdum sed orci nunc, mattis. Ipsum
                viverra viverra neque adipiscing arcu, quam dictum. Dui mi viverra
                dui, sit accumsan, tincidunt massa. Dui cras magnis.
            </p>
        </div>
    </div>
    <div class="categories-container">
        <div class="child-one">
            <div>Choose Different Categories </div>
        </div>
        <div class="job-categories">
            <div class="job-category">
                <img src="images/untitled (1).png" alt="" />
                <p>Web Development</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (2).png" alt="" />
                <p>Mobile App Development</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (3).png" alt="" />
                <p>Graphics Design</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (4).png" alt="" />
                <p>Article Writing</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (5).png" alt="" />
                <p>Illustrations</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (6).png" alt="" />
                <p>Flyers and Vouchers</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (7).png" alt="" />
                <p>Video Editing</p>
            </div>
            <div class="job-category">
                <img src="images/untitled (8).png" alt="" />
                <p>Cartoon Animation</p>
            </div>
        </div>
        <div class="header-buttons two">
            <button>More categories</button>
        </div>
    </div>
    <div class="newspaper-container">
        <div class="newspaper-body">
            <p class="newspaper-title">Newsletter Subscription</p>
            <p class="newspaper-content">
                Subscribe to our newsletter to get new freelance work and projects
            </p>
            <div class="input-container">
                <input type="text" placeholder="Enter your email address" />
            </div>
            <div class="subscription-button-container">
                <button>Subscribe</button>
            </div>
        </div>
    </div>
</body>
</html>

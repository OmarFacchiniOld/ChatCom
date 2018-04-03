<%-- 
    Document   : mainpage
    Created on : 3-apr-2018, 20.10.40
    Author     : Abate Simone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container-fluid">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
        <img class="img-carousel d-block w-100" src="test.jpeg" alt="Second slide">
        <div class="container">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="underline">Example</h5>
                        <p class="underline">an example text</p>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
    </div>
    <div class="carousel-item">
      <img class="img-carousel d-block w-100" src="test.jpeg" alt="Second slide">
      <div class="container">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="underline">Example</h5>
                        <p class="underline">an example text</p>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
    </div>
    <div class="carousel-item">
      <img class="img-carousel d-block w-100" src="test.jpeg" alt="Third slide">
      <div class="container">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="underline">Example</h5>
                        <p class="underline">an example text</p>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
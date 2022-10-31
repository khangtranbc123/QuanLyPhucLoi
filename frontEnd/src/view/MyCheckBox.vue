<el-popover placement="left" width="350" trigger="click" style="margin-right: 20px;">
  <div style="font-size: 30px" class="hr-detail__title">
    <Strong>Phúc lợi đang hưởng</Strong>
  </div>
  <span
    ><Strong style="font-size: 20px; color: brown;"
      ><i class="el-icon-success"></i> Phúc lợi cá nhân
      hóa</Strong
    ></span
  >
  <ul
    v-for="x in welfares"
    :key="x.id"
    style="list-style-type: none"
  >
    <li>
      <Strong>Phúc lợi</Strong> {{ x.welfare.name }}
      <div></div>
      <Strong>Giá trị</Strong> {{formatCurrency(x.welfare.price) }}
      <i class="el-icon-money"></i>
    </li>
  </ul>
  <br />
  <span
    ><Strong style="font-size: 20px">
      <i class="el-icon-circle-plus"></i> Phúc lợi chung</Strong
    ></span
  >
  <ul
    v-for="x in gerenalWelfares"
    :key="x.id"
    style="list-style-type: none"
  >
  <div class="card" style="width: 18rem;">
    <div class="card-body">
      <h5 class="card-title"> Phúc lợi {{ x.name }}</h5>
      <p class="card-text"> <Strong>Giá trị</Strong> {{formatCurrency(x.price)}}</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
  </div>
    <!-- <li>
      <Strong>Phúc lợi</Strong> {{ x.name }}
      <div></div>
      <Strong>Giá trị</Strong> {{formatCurrency(x.price)}}
      <i class="el-icon-money"></i>
    </li> -->
  </ul>
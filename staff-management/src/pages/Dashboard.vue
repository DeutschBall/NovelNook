<template>
  <div class="content">
    <div class="md-layout">
      <!-- 数据看板 -->
      <div class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-20">
        <stats-card data-background-color="blue">
          <template slot="header">
            <span class="material-icons" style="width:1em;height:1em;font-size: 48px;">
              groups
            </span>
          </template>

          <template slot="content">
            <p class="category" style="font-size: 25px;">Staff</p>
            <h3 class="title" style="margin-top: 5px;">{{ staffNum }}</h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>update</md-icon>
              Just Updated
            </div>
          </template>
        </stats-card>
      </div>


      <!-- 表格部分 -->
      <div class="table_container">
        <el-container>
          <!-- <el-header>
                导航栏
            </el-header> -->
          <el-main>
            <!--搜索表单-->
            <div class="search">
              <div class="search_input">
                <el-form :inline="true" :model="searchStaff.username">
                <el-form-item label="username">
                  <el-input v-model="searchStaff.username" placeholder="Please input the username"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="selectByuserName(searchStaff.username)">Search</el-button>
                  <el-button type="info" @click="clear">Clear</el-button>
                </el-form-item>
              </el-form>
              </div>
            </div>
            <br>
            <!-- 添加员工 -->
            <el-button type="primary"> <router-link :to="{name:'User Profile'}">Add New Staff</router-link>   </el-button>

            <el-dialog title="Add New Staff" :visible.sync="dialogAddVisible">
              <el-form :model="form">
                <el-upload class="avatar-uploader" action="https://localhost:8080/" :show-file-list="false"
                  :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                  <img v-if="imageUrl" :src="imageUrl" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>

                <el-form-item label="firstName" :label-width="formLabelWidth">
                  <el-input v-model="form.firstName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="lastName" :label-width="formLabelWidth">
                  <el-input v-model="form.lastName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="userName" :label-width="formLabelWidth">
                  <el-input v-model="form.userName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="password" :label-width="formLabelWidth">
                  <el-input v-model="form.passWord" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="telephone" :label-width="formLabelWidth">
                  <el-input v-model="form.telephone" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="email" :label-width="formLabelWidth">
                  <el-input v-model="form.email" auto-complete="off"></el-input>
                </el-form-item>

              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogAddVisible = false">Quit</el-button>
                <el-button type="primary" @click="handleSetAdd(scope.$index, scope.row)">Save</el-button>
              </div>
            </el-dialog>
            <br>
            <!--表格-->
            <template>
              <el-table :data="tableData" style="width: 100%" border>
                <el-table-column prop="userid" label="UserID" align="center"></el-table-column>
                <el-table-column label="Avatar" align="center">
                  <template slot-scope="scope">
                    <el-image style="width: 40px; height: 40px; border: none; cursor: pointer"
                      :src="baseUrl + scope.row.avatar"></el-image>

                  </template>
                </el-table-column>
                <el-table-column prop="firstname" label="FirstName" align="center"></el-table-column>
                <el-table-column prop="lastname" label="LastName" align="center"></el-table-column>
                <!-- <el-table-column  label="FullName" align="center">{{ scope.row.firstName }} + {{ scope.row.lastName }}</el-table-column> -->

                <!-- 详细信息表单 -->
                <el-table-column align="center" label="Operation">
                  <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">Look
                      More</el-button>
                    <el-dialog title="Detailed Information" :visible.sync="dialogFormVisible">
                      <el-form :model="form">
                        <!-- 表单内头像 -->
                        <el-form-item label="Avatar" :label-width="formLabelWidth">
                          <el-image style="width: 40px; height: 40px; border: none; cursor: pointer" :src="baseUrl + form.Avatar"></el-image>
                        </el-form-item>
                        <!-- 表单内头像 -->
                        <el-form-item label="firstName" :label-width="formLabelWidth">
                          <el-input v-model="form.firstName" auto-complete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="lastName" :label-width="formLabelWidth">
                          <el-input v-model="form.lastName" auto-complete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="userName" :label-width="formLabelWidth">
                          <el-input v-model="form.userName" auto-complete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="password" :label-width="formLabelWidth">
                          <el-input v-model="form.passWord" auto-complete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="telephone" :label-width="formLabelWidth">
                          <el-input v-model="form.telephone" auto-complete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="email" :label-width="formLabelWidth">
                          <el-input v-model="form.email" auto-complete="off"></el-input>
                        </el-form-item>

                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">Quit</el-button>
                        <el-button type="primary" @click="handleSet(scope.$index, scope.row)">Save</el-button>
                      </div>
                    </el-dialog>
                    <el-button type="danger" size="small" @click="bandDeleteName(scope.row)">Delete</el-button>
                    <el-dialog title="Prompt" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
                      <span>Do you confirm to delete this staff?</span>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="dialogVisible = false">Quit</el-button>
                        <el-button type="primary" @click="deleteByuserName()">Confirm</el-button>
                      </span>
                    </el-dialog>
                  </template>
                </el-table-column>
              </el-table>
            </template>

            <!--分页工具条-->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :background="background"
              :current-page="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="10"
              layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
            </el-pagination>

          </el-main>

        </el-container>

      </div>
    </div>
  </div>
</template>

<script>
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable,
} from "@/components";
import axios from "axios";
export default {
  components: {
    StatsCard,
    ChartCard,
    NavTabsCard,
    NavTabsTable,
    OrderedTable,
  },
  data() {
    return {
      staffNum: 0, // 员工人数
      baseUrl: 'http://localhost:8080/',
      imageUrl: '', // 头像上传的路径
      dialogTableVisible: false,
      dialogAddVisible: false, // 添加员工表单
      dialogFormVisible: false, // 详情表单
      dialogVisible: false,   // 删除对话框
      formLabelWidth: '120px',
      pageSize: 10, // 每页显示10条
      totalCount: 100, // 总记录数初始化为100
      currentPage: 1, // 当前页码
      tableData: [], // 接收返回的数据
      deleteName: "", // 删除的username名称
      form: { // 用来显示详情数据和更新数据
        Avatar: "",
        userName: "",
        passWord: "",
        firstName: "",
        lastName: "",
        telephone: "",
        email: ""
      },
      searchStaff: { // 接收输入的username
        username: ""
      },
      staff: {
        image: ""
      },


      dailySalesChart: {
        data: {
          labels: ["M", "T", "W", "T", "F", "S", "S"],
          series: [[12, 17, 7, 17, 23, 18, 38]],
        },
        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0,
          }),
          low: 0,
          high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
          },
        },
      },
      dataCompletedTasksChart: {
        data: {
          labels: ["12am", "3pm", "6pm", "9pm", "12pm", "3am", "6am", "9am"],
          series: [[230, 750, 450, 300, 280, 240, 200, 190]],
        },

        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0,
          }),
          low: 0,
          high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
          },
        },
      },
      emailsSubscriptionChart: {
        data: {
          labels: [
            "Ja",
            "Fe",
            "Ma",
            "Ap",
            "Mai",
            "Ju",
            "Jul",
            "Au",
            "Se",
            "Oc",
            "No",
            "De",
          ],
          series: [
            [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895],
          ],
        },
        options: {
          axisX: {
            showGrid: false,
          },
          low: 0,
          high: 1000,
          chartPadding: {
            top: 0,
            right: 5,
            bottom: 0,
            left: 0,
          },
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function (value) {
                  return value[0];
                },
              },
            },
          ],
        ],
      },
    };
  },
  mounted() {
    // 获得staff人数和表格数据
    let that = this;
    axios.get("http://localhost:8080/admin/staff")
      .then(function (resp) {
        if (resp.data.code === 1) {
          that.staffNum = resp.data.data.length;
          that.tableData = resp.data.data;
          console.log(resp);
        } else {
          alert(resp.data.msg);
        }

      })
  },
  methods: {
    selectByuserName(userName) {
      let that = this;
      axios.get("http://localhost:8080/admin/staff/" + userName).then(function (resp) {
        if (resp.data.code === 1) {
          that.tableData = resp.data.data;
        } else {
          alert(resp.data.msg);
        }
      })
    },
    deleteByuserName() {
      this.dialogVisible = false;
      let that = this;
      console.log(that.deleteName);
      axios.delete("http://localhost:8080/admin/staff/" + that.deleteName)
        .then(function (resp) {
          if (resp.data.code === 1) {
            axios.get("http://localhost:8080/admin/staff")
              .then(function (response) {
                if (response.data.code === 1) {
                  that.tableData = response.data.data;
                } else {
                  alert(response.data.msg);
                }

              })
            that.reload();
          } else {
            alert(resp.data.msg);
          }

        })
    },
    clear() {
      this.searchStaff.username = ''
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true
      this.form.Avatar = row.avatar
      this.form.firstName = row.firstname
      this.form.lastName = row.lastname
      this.form.userName = row.username
      this.form.passWord = row.password
      this.form.email = row.email
      this.form.telephone = row.telephone
    },
    bandDeleteName(row) {
      this.dialogVisible = true;
      this.deleteName = row.username;
    },
    handleSet(index, row) {
      let that = this;
      var params = {
        avatar: this.form.Avatar,
        username: this.form.userName,
        password: this.form.passWord,
        firstname: this.form.firstName,
        lastname: this.form.lastName,
        telephone: this.form.telephone,
        email: this.form.email
      }
      axios.put("http://localhost:8080/admin/staff", params).then(function (resp) {
        if (resp.data.code === 1) {
          that.dialogFormVisible = false;
          // alert("Update succseefully");
          axios.get("http://localhost:8080/admin/staff")
            .then(function (resp) {
              if (resp.data.code === 1) {
                that.tableData = resp.data.data;
              } else {
                alert(resp.data.msg);
              }

            })
          that.reload();
        } else {
          alert(resp.data.msg);
        }
      })
    }
  },
}
</script>


<style lang="scss" scoped>
.table_container{
  width: 60%;
  margin-left: 5%;
}

.search_input{
  margin-top:5% ;
}

</style>
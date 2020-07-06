const { TabPane } = antd.Tabs;
const { Search } = antd.Input;

class MedicalAdviceSearch extends React.Component{

    constructor(props) {
        super(props);
    }

    render(){
        return(
            <div>
                <antd.Tabs defaultActiveKey="1"  tabPosition={"left"}>
                    <TabPane tab="医嘱" key="1">
                        <Ord/>
                    </TabPane>
                    <TabPane tab="检查" key="2">
                        Content of Tab Pane 2
                    </TabPane>
                    <TabPane tab="检验" key="3">
                        Content of Tab Pane 3
                    </TabPane>
                </antd.Tabs>
            </div>
        );
    }

}

const treeData = [];

const data = [];
const columns = [
    {
        title: '医嘱名称',
        dataIndex: 'name',
        key: 'name',
        render: text => <a>{text}</a>,
    },
    {
        title: '用量',
        dataIndex: 'dosage',
        key: 'dosage',
        render: text => <a>{text}</a>,
    },
    {
        title: '单位',
        dataIndex: 'unit',
        key: 'unit',
        render: text => <a>{text}</a>,
    },
    {
        title: '用法',
        dataIndex: 'usage',
        key: 'usage',
        render: text => <a>{text}</a>,
    },
    {
        title: '频次',
        dataIndex: 'frequency',
        key: 'frequency',
        render: text => <a>{text}</a>,
    },
    {
        title: '执行科室',
        dataIndex: 'exeDept',
        key: 'exeDept',
        render: text => <a>{text}</a>,
    },

];

const ordColumns = [
    {
        title: '模板',
        dataIndex: 'name',
        key: 'name',
        render: text => <a>{text}</a>,
    },
];
const ordData = [];

class Ord extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data: data,
            ordData:ordData
        }
    }

    componentDidMount() {

        this.listEmpOrd();
    }

    componentWillUnmount() {
        this.serverRequest.abort();
    }

    // 获取个人模板
    listEmpOrd(){
        // this.serverRequest = $.get(global.patientInfo+"/nhis/mobile/ord/set/emp?pkEmp=74f80fd350154f278c291828c7853ead", function (result) {
        //     if(result.code==400){
        //         antd.notification.open({
        //             message: '提示',
        //             description: result.msg,
        //         });
        //     }
        //     if(result.code==200){
        //         this.setState({
        //             ordData: result.data,
        //         });
        //     }
        //
        // }.bind(this));
        console.log(123);
            $.ajax({
                url: global.patientInfo+"/nhis/mobile/ord/set/emp?pkEmp=74f80fd350154f278c291828c7853ead",
                dataType: 'json',
                cache: false,
                success: function(data) {
                    this.setState({ordData: data.data});   // 注意这里
                }.bind(this)
            });
    }

    onLoadData(){
        console.log(123);
        return new Promise(function() {

            resolve();
            $.ajax({
                url: global.patientInfo+"/nhis/mobile/ord/set/emp?pkEmp=74f80fd350154f278c291828c7853ead",
                dataType: 'json',
                cache: false,
                success: function(data) {
                    this.setState({treeData: data.data});   // 注意这里
                }.bind(this),
            });
        });

    }




    onSelect (selectedKeys, info){


        console.log(selectedKeys);
    };

    render(){
        return(
            <div>
                <div style={{width:500}}>
                    <Search placeholder="input search text"  enterButton />
                </div>
                <div style={{marginTop:20}}>
                    <div>
                        <antd.Table
                            bordered
                            columns={columns}
                            dataSource={this.state.data}
                            pagination={false}
                            scroll={{y: 300 }}
                        />
                    </div>
                    <div style={{marginTop:20}}>
                        <antd.Row>
                            <antd.Col span={12}>
                                <antd.Radio.Group defaultValue="a" buttonStyle="solid">
                                    <antd.Radio.Button value="a">长期</antd.Radio.Button>
                                    <antd.Radio.Button value="b">临时</antd.Radio.Button>
                                </antd.Radio.Group>
                            </antd.Col>
                            <antd.Col span={12}>
                                <antd.Button type="primary">确定</antd.Button>
                                <antd.Button type="primary">取消</antd.Button>
                            </antd.Col>
                        </antd.Row>
                    </div>
                </div>
            </div>
        );
    }
}
ReactDOM.render( <MedicalAdviceSearch />, document.getElementById('root'));
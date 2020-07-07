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

function confirm(e) {
    console.log(e);
    window.location.href = "/mobile/drug/index"
}

function cancel(e) {
    console.log(e);

}
const treeData = [];

const data = [];
const columns = [
    {
        title: '医嘱名称',
        dataIndex: 'name',
        key: 'name',
        render: text => <antd.Popconfirm
            title={text}
            onConfirm={confirm}
            onCancel={cancel}
            okText="选中药品"
            cancelText="选中非药品"
            >
            <a href="#">{text}</a>
        </antd.Popconfirm>,
    },
    {
        title: '规格',
        dataIndex: 'spec',
        key: 'spec',
        render: text => <a>{text}</a>,
    },
    {
        title: '包装单位',
        dataIndex: 'unit',
        key: 'unit',
        render: text => <a>{text}</a>,
    },
    {
        title: '描述',
        dataIndex: 'desc',
        key: 'desc',
        render: text => <a>{text}</a>,
    },
    {
        title: '参考价格',
        dataIndex: 'price',
        key: 'price',
        render: text => <a>{text}</a>,
    },
    {
        title: '库存量',
        dataIndex: 'amount',
        key: 'amount',
        render: text => <a>{text}</a>,
    },
    {
        title: '医保类型',
        dataIndex: 'medicareType',
        key: 'medicareType',
        render: text => <a>{text}</a>,
    },

];

const ordData = [];

class Ord extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data: data,
            ordData:ordData,
            searchValue: "",
        }
    }

    componentDidMount() {
        this.listOrd($("#value").text())

    }

    componentWillUnmount() {
        this.serverRequest.abort();
    }

    onSelect (selectedKeys, info){
        console.log(selectedKeys);
    };

    listOrd(value){
        console.log(value);
        if(value!=""){
            $.ajax({
                url: "/nhis/mobile/ord/pd/list?spCode="+value,
                dataType: 'json',
                cache: false,
                success: function(data) {
                    this.setState({
                        ordData: data.data,
                    });

                }.bind(this)
            });
        }

    }

    render(){
        return(
            <div>
                <div style={{width:800}}>
                    <antd.Row>
                        <antd.Col span={12}>
                            <antd.Input id="search" placeholder="Basic usage" />
                        </antd.Col>
                        <antd.Col span={4}>
                            <antd.Button type="primary"  onClick={()=>this.listOrd($("#search").val())}>搜索</antd.Button>
                        </antd.Col>
                        <antd.Col span={1}></antd.Col>
                        <antd.Col span={4}>
                            <antd.Button type="primary">取消</antd.Button>
                        </antd.Col>
                    </antd.Row>


                </div>
                <div style={{marginTop:20}}>
                    <div>
                        <antd.Table
                            bordered
                            columns={columns}
                            dataSource={this.state.ordData}
                            pagination={false}
                            scroll={{y: 300 }}
                        />
                    </div>
                </div>
            </div>
        );
    }
}
ReactDOM.render( <MedicalAdviceSearch />, document.getElementById('root'));
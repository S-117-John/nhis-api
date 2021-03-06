const columns = [
    {
        title: '组',
        dataIndex: 'group',
        key: 'group',
        width: 50,
        fixed: 'left',
    },

    {
        title: '分类',
        dataIndex: 'classification',
        key: 'classification',
        width: 60,
        fixed: 'left',
    },
    {
        title: '医嘱',
        dataIndex: 'nameOrd',
        key: 'nameOrd',
        render: text => <a>{text}</a>,
        width: 200,
        fixed: 'left',
    },
    {
        title: '长',
        key: 'euAlways',
        dataIndex: 'euAlways',
        render: text => {
            let color = text == "0"  ? 'geekblue' : 'green';
            if (text === '0') {
                text = '长期';
            }else if(text === '1'){
                text = '临时';
            }
            return (
                <antd.Tag color={color} key={text}>
                    {text}
                </antd.Tag>
            );
        },
    },
    {
        title: '开始时间',
        key: 'dateStart',
        dataIndex: 'dateStart',
        width: 200,
    },

    {
        title: '用量',
        dataIndex: 'quan',
        key: 'quan',
        render: text => <a>{text}</a>,
    },
    {
        title: '用法',
        dataIndex: 'codeSupply',
        key: 'codeSupply',
        render: text => <a>{text}</a>,
    },
    {
        title: '频次',
        dataIndex: 'codeFreq',
        key: 'codeFreq',
        render: text => <a>{text}</a>,
    },
    {
        title: '首',
        dataIndex: 'firstNum',
        key: 'firstNum',
        render: text => <a>{text}</a>,
    },
    {
        title: '停止时间',
        dataIndex: 'dateStop',
        key: 'dateStop',
        render: text => <a>{text}</a>,
    },
    {
        title: '末',
        dataIndex: 'lastNum',
        key: 'lastNum',
        render: text => <a>{text}</a>,
    },
    {
        title: '开立人',
        dataIndex: 'nameEmpOrd',
        key: 'nameEmpOrd',
        render: text => <a>{text}</a>,
    },
    {
        title: '签署',
        dataIndex: 'nameEmpOrd',
        key: 'nameEmpOrd',
        render: text => <a>{text}</a>,
    },
    {
        title: '停嘱',
        dataIndex: 'nameEmpStop',
        key: 'nameEmpStop',
        render: text => <a>{text}</a>,
    },
    {
        title: '操作',
        dataIndex: 'action',
        key: 'action',
        render: (text) => (
            <antd.Space size="middle">
                <a>详情</a>
            </antd.Space>
        ),
        width: 100,
        fixed: 'right',
    },
];

let tableData = [];



class Index extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            gender: "",
            bed: "",
            age: "",
            tableData: tableData,
        };
    }

    componentDidMount() {
        this.serverRequest = $.get(global.patientInfo+"nhis/mobile/patient?pkPv="+document.getElementById('id').innerText, function (result) {
            console.log(result);
            if(result.code==400){
                antd.notification.open({
                    message: '提示',
                    description: result.msg,
                });
            }
            if(result.code==200){
                this.setState({
                    name: result.data.namePi,
                    gender: result.data.gender,
                    bed: result.data.bedNo,
                    age: result.data.agePv,
                });
            }

        }.bind(this));
        this.listPatientOrder();
    }

    componentWillUnmount() {
        this.serverRequest.abort();
    }

    listPatientOrder(){
        this.serverRequest = $.get(global.patientInfo+"/nhis/mobile/ord?pkPv="+document.getElementById('id').innerText, function (result) {
            console.log(result);
            if(result.code==400){
                antd.notification.open({
                    message: '提示',
                    description: result.msg,
                });
            }
            if(result.code==200){
                this.setState({
                    tableData: result.data
                });
            }

        }.bind(this));
    }

    // 跳转到医嘱界面
    toMedicalAdvice(){
        // global.patientInfo+
        window.location.href= "/mobile/advice?id="+document.getElementById('id').innerText;
    }

    render() {
        return (
            <div style={{margin: 20}}>
                <div>
                    <antd.Row>
                        <antd.Col span={6}>
                            <div>
                                <antd.Radio.Group defaultValue="a" buttonStyle="solid">
                                    <antd.Radio.Button value="a">全部</antd.Radio.Button>
                                    <antd.Radio.Button value="b">临时</antd.Radio.Button>
                                    <antd.Radio.Button value="c">长期</antd.Radio.Button>
                                </antd.Radio.Group>
                            </div>
                        </antd.Col>

                        <antd.Col span={6}>
                            <div>
                                <antd.Radio.Group defaultValue="a" buttonStyle="solid">
                                    <antd.Radio.Button value="a">全部</antd.Radio.Button>
                                    <antd.Radio.Button value="b">当前</antd.Radio.Button>
                                </antd.Radio.Group>
                            </div>
                        </antd.Col>

                        <antd.Col span={12}>
                            <div>
                                <antd.Button type="primary" style={{marginLeft:20}} onClick={this.toMedicalAdvice}>新医嘱</antd.Button>
                                <antd.Button type="primary" style={{marginLeft:20}}>停嘱</antd.Button>
                                <antd.Button type="primary" style={{marginLeft:20}}>签署</antd.Button>
                                <antd.Button type="primary" style={{marginLeft:20}}>删除</antd.Button>
                            </div>

                        </antd.Col>
                    </antd.Row>
                    <div style={{marginTop: 20}}>
                        <antd.Table
                            columns={columns}
                            dataSource={this.state.tableData}
                            scroll={{ x: 1500,y: 500 }}
                            pagination={ false }
                            bordered />
                    </div>
                </div>
            </div>
        );
    }
}


ReactDOM.render( <Index />, document.getElementById('root'));



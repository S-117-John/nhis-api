let columns = [
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
        dataIndex: 'name',
        key: 'name',
        render: text => <a>{text}</a>,
        width: 150,
        fixed: 'left',
    },
    {
        title: '长',
        key: 'tags',
        dataIndex: 'tags',
        render: text => {
            let color = text == "长"  ? 'geekblue' : 'green';
            // if (tag === 'loser') {
            //     color = 'volcano';
            // }
            return (
                <antd.Tag color={color} key={text}>
                    {text}
                </antd.Tag>
            );
        },
    },
    {
        title: '开始时间',
        key: 'startTime',
        dataIndex: 'startTime',
    },

    {
        title: '用量',
        dataIndex: 'dosage',
        key: 'dosage',
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
        title: '首',
        dataIndex: 'first',
        key: 'first',
        render: text => <a>{text}</a>,
    },
    {
        title: '停止时间',
        dataIndex: 'endTime',
        key: 'endTime',
        render: text => <a>{text}</a>,
    },
    {
        title: '末',
        dataIndex: 'last',
        key: 'last',
        render: text => <a>{text}</a>,
    },
    {
        title: '开立人',
        dataIndex: 'creator',
        key: 'creator',
        render: text => <a>{text}</a>,
    },
    {
        title: '签署',
        dataIndex: 'sign',
        key: 'sign',
        render: text => <a>{text}</a>,
    },
    {
        title: '停嘱',
        dataIndex: 'stop',
        key: 'stop',
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

let data = [
    {
        key: '1',
        name: '5%葡萄糖注射液/250ml/袋',
        group: 1,
        classification: '西药',
        tags: "长",
        startTime: '05-30 12:00',
        dosage: '250ml',
        usage: '输液(免费)',
        frequency: 'qd',
        first: '1',
        endTime: '05-30 12:00',
        last: '1',
        creator: '陆丞燕',
        sign: '陆丞燕',
        stop: '陆丞燕',

    },
    {
        key: '2',
        name: '5%葡萄糖注射液/250ml/袋',
        group: 2,
        classification: '西药',
        tags: "长",
        startTime: '05-30 12:00',
        dosage: '250ml',
        usage: '输液(免费)',
        frequency: 'qd',
        first: '1',
        endTime: '05-30 12:00',
        last: '1',
        creator: '陆丞燕',
        sign: '陆丞燕',
        stop: '陆丞燕',

    },
    {
        key: '3',
        name: '5%葡萄糖注射液/250ml/袋',
        group: 3,
        classification: '西药',
        tags: "长",
        startTime: '05-30 12:00',
        dosage: '250ml',
        usage: '输液(免费)',
        frequency: 'qd',
        first: '1',
        endTime: '05-30 12:00',
        last: '1',
        creator: '陆丞燕',
        sign: '陆丞燕',
        stop: '陆丞燕',

    },
];


class Index extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: ""
        };
    }

    componentDidMount() {
        this.serverRequest = $.get(global.patientInfo+"/nhis-mobile-patient?pkPv="+document.getElementById('id').innerText, function (result) {
            console.log(result);
            this.setState({
                name: result.data.namePi
            });
        }.bind(this));
    }

    componentWillUnmount() {
        this.serverRequest.abort();
    }

    render() {
        return (
            <div>
                <div>
                    <antd.Row>
                        <antd.Col span={6}><h1>{this.state.name}</h1></antd.Col>
                        <antd.Col span={6}><h3>31</h3></antd.Col>
                        <antd.Col span={6}> <h3>男</h3></antd.Col>
                        <antd.Col span={6}> <h3>3岁</h3></antd.Col>
                    </antd.Row>
                    <h3>住院号：13423  身份：灵璧医保  入院：2020-5-30 12:23   诊断： 肺炎</h3>
                </div>
                <div>
                    <antd.Row>
                        <antd.Col span={12}>
                            <div>
                                <antd.Radio.Group defaultValue="a" buttonStyle="solid">
                                    <antd.Radio.Button value="a">全部</antd.Radio.Button>
                                    <antd.Radio.Button value="b">临时</antd.Radio.Button>
                                    <antd.Radio.Button value="c">长期</antd.Radio.Button>
                                </antd.Radio.Group>
                            </div>
                        </antd.Col>
                        <antd.Col span={12}>
                            <div>
                                <antd.Button type="primary">停嘱</antd.Button>
                                <antd.Button>签署</antd.Button>
                                <antd.Button type="dashed">删除</antd.Button>
                            </div>

                        </antd.Col>
                    </antd.Row>
                    <div>
                        <antd.Table
                            columns={columns}
                            dataSource={data}
                            scroll={{ x: 1500 }}
                            pagination={ false }
                            bordered />
                    </div>
                </div>
            </div>
        );
    }
}


ReactDOM.render( <Index />, document.getElementById('root'));



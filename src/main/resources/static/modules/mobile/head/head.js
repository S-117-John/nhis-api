class Head extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            gender: "",
            bed: "",
            age: "",
        };
    }

    componentDidMount() {
        this.serverRequest = $.get(global.patientInfo+"/nhis/mobile/patient?pkPv="+document.getElementById('id').innerText, function (result) {
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

    }

    componentWillUnmount() {
        this.serverRequest.abort();
    }

    render() {
        return (
            <div style={{margin: 20}}>
                <antd.Row>
                    <antd.Col span={6}><h1>{this.state.name}</h1></antd.Col>
                    <antd.Col span={6}><h3>{this.state.bed}床</h3></antd.Col>
                    <antd.Col span={6}> <h3>{this.state.gender}</h3></antd.Col>
                    <antd.Col span={6}> <h3>{this.state.age}</h3></antd.Col>
                </antd.Row>
                <h3>住院号：13423  身份：灵璧医保  入院：2020-5-30 12:23   诊断： 肺炎</h3>
            </div>
        );
    }
}

ReactDOM.render( <Head />, document.getElementById('head'));
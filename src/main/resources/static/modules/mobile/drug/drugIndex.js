const { Option } = antd.Select;

function onChange(date, dateString) {
    console.log(date, dateString);
}

class DrugIndex extends React.Component{

    render(){
        return(
            <div style={{margin:15}}>

                <div>
                    <antd.Button style={{marginRight:10}} type="primary">新增</antd.Button>
                    <antd.Button style={{marginRight:10}} type="primary">保存</antd.Button>
                    <antd.Button style={{marginRight:10}} type="primary">签署</antd.Button>
                    <antd.Button style={{marginRight:10}} type="primary">删除</antd.Button>
                    <antd.Button style={{marginRight:10}} type="primary">返回</antd.Button>
                </div>

                <div>
                    <antd.Radio.Group defaultValue="a" buttonStyle="solid">
                        <antd.Radio.Button value="a">长期</antd.Radio.Button>
                        <antd.Radio.Button value="b">临时</antd.Radio.Button>
                    </antd.Radio.Group>

                    <span style={{marginLeft:20}}>开始时间：</span>
                    <antd.DatePicker onChange={onChange} />
                    {/*<span style={{marginLeft:20}}>频次：</span>*/}
                    {/*<antd.Select defaultValue="lucy" style={{ width: 120 }}>*/}
                    {/*    <Option value="jack">Jack</Option>*/}
                    {/*    <Option value="lucy">Lucy</Option>*/}
                    {/*    <Option value="disabled" disabled>*/}
                    {/*        Disabled*/}
                    {/*    </Option>*/}
                    {/*    <Option value="Yiminghe">yiminghe</Option>*/}
                    {/*</antd.Select>*/}
                    {/*<span style={{marginLeft:20}}>用法：</span>*/}
                    {/*<antd.Select defaultValue="lucy" style={{ width: 120 }} >*/}
                    {/*    <Option value="jack">Jack</Option>*/}
                    {/*    <Option value="lucy">Lucy</Option>*/}
                    {/*    <Option value="disabled" disabled>*/}
                    {/*        Disabled*/}
                    {/*    </Option>*/}
                    {/*    <Option value="Yiminghe">yiminghe</Option>*/}
                    {/*</antd.Select>*/}

                    {/*<antd.Input addonBefore="首:"  defaultValue="1" style={{width:100,marginLeft:20}}/>*/}

                    {/*<antd.Button style={{marginLeft:10}} type="primary">添加药品</antd.Button>*/}
                </div>

                {/*<div>*/}
                {/*    <div>*/}
                {/*        <div>*/}
                {/*            <span>氯化钠注射液</span>*/}

                {/*            <antd.Input addonBefore="用量:" addonAfter="ml"  defaultValue="1" style={{width:200,marginLeft:20}}/>*/}

                {/*            <antd.Input addonBefore="备注:"   defaultValue="1" style={{width:200,marginLeft:20}}/>*/}
                {/*        </div>*/}
                {/*        <span>【规格】0.9% 250ml/袋   【单价】5.000000元/袋   【剂量】250.0000ml   【贵重级别】普通物品</span>*/}
                {/*    </div>*/}
                {/*    <div style={{marginLeft:20}}>*/}
                {/*        <div>*/}
                {/*            <span>加急：</span> <antd.Switch checkedChildren="开启" unCheckedChildren="关闭" defaultChecked />*/}
                {/*        </div>*/}
                {/*        <div>*/}
                {/*            <span>自备：</span> <antd.Switch checkedChildren="开启" unCheckedChildren="关闭" defaultChecked />*/}
                {/*        </div>*/}
                {/*    </div>*/}
                {/*    <div style={{marginLeft:20}}>*/}
                {/*        <antd.Button type="primary">抗菌药信息</antd.Button>*/}
                {/*        <antd.Button danger type="primary">删除</antd.Button>*/}
                {/*    </div>*/}
                {/*</div>*/}
            </div>
        );
    }
}

ReactDOM.render( <DrugIndex />, document.getElementById('root'));
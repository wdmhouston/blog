//Solve vue cli 3.0 cache issue, https://www.jianshu.com/p/e633d49d2883
const Timestamp = new Date().getTime();
module.exports = {
    configureWebpack: {
        output: {
            filename: `js/[name].${Timestamp}.js`,
            chunkFilename: `js/[name].${Timestamp}.js`
        }
    }
};
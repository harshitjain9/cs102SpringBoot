var path = require('path');

module.exports = {
    entry: './src/main/js/src/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    devServer: {
        historyApiFallback: true
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.(png|svg|jpg|gif|eot|otf|ttf|woff|woff2)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {}
                    }
                ]
            }
        ]
    }
};

// var HtmlWebpackPlugin = require('html-webpack-plugin');
// const path = require("path");

// module.exports = {
//     devtool: 'inline-source-map',
//     entry: './src/main/js/src/index.js',
//     output: {
//         path: path.join(__dirname, '/dist'),
//         filename: 'index_bundle.js'
//     },
//     mode: 'development',
//     resolve: {
//         extensions: ['.js', '.jsx']
//     },
//     module: {
//         rules: [
//             {
//                 test: /\.jsx?$/,
//                 exclude: /node_modules/,
//                 loader: 'babel-loader'
//             },
//             {
//                 test: /\.css$/,
//                 use: ['style-loader', 'css-loader']
//             },
//             {
//                 test: /\.(svg|png|jpg|jpeg|gif)$/,
//                 use: {
//                     loader: "file-loader",
//                     options: {
//                         name: "[name].[hash].[ext]",
//                         outputPath: "imgs"
//                     }
//                 }
//             }
//         ]
//     },
//     plugins: [new HtmlWebpackPlugin({
//         // template: './src/index.html',
//         // favicon: './src/public/favicon4.png',
//     })],
//     devServer: {
//         historyApiFallback: true
//     },
//     externals: {
//         // global app config object
//         // config: JSON.stringify({
//         //     apiUrl: '/api'
//         // })
//         config: JSON.stringify({
//             apiUrl: 'http://localhost:9000'
//         })
//     }
// }


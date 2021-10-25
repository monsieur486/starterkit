const { src, dest, watch, task, gulp } = require('gulp');
const sass = require('gulp-sass');
const csscomb = require('gulp-csscomb');
const cssbeautify = require('gulp-cssbeautify');
const csso = require('gulp-csso');
const rename = require('gulp-rename');
const uglify = require('gulp-uglify');  // minify js
const imagemin = require('gulp-imagemin');
const sourcemaps = require('gulp-sourcemaps');
const concat = require('gulp-concat');
const minify = require('gulp-minify');
const del = require("del");
const merge = require("merge-stream");
const { series } = require('gulp');



// Variables de chemins
const source = './src'; // dossier de travail
const destination = './src/main/resources/static'; // dossier à livrer
const vendorDest = destination + '/js';


function build(cb) {
    css();
    js();
    img()
    cb();
}

// Clean vendor
function clean() {
    return del([vendorDest]);
}

// Bring third party dependencies from node_modules into vendor directory
function modules() {
    // ChartJS
    const chartJS = src('node_modules/chart.js/dist/*.js')
        .pipe(dest(vendorDest + '/chart'));
    // dataTables
    const dataTables = src([
        'node_modules/datatables.net/js/*.js',
        'node_modules/datatables.net-bs4/js/*.js',
        'node_modules/datatables.net-bs4/css/*.css'
    ])
        .pipe(dest(vendorDest + '/table'));
    // Font Awesome
    const fontAwesome = src('node_modules/@fortawesome/fontawesome-free/js/*')
        .pipe(dest(vendorDest + '/font'));
    // jQuery
    const jquery = src([
        'node_modules/jquery/dist/*',
        '!node_modules/jquery/dist/core.js'
    ])
        .pipe(dest(vendorDest + '/jquery'));
    return merge(chartJS, dataTables, fontAwesome, jquery);
}

function css() {
    return src(source + '/assets/scss/style.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(cssbeautify({ indent: '  ' }))
        .pipe(csso())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(sourcemaps.init({ loadMaps: false }))
        .pipe(sourcemaps.write('../maps'))
        .pipe(dest(destination + '/css/'));
}
function js() {
    return src(source + '/assets/js/*.js')
        .pipe(uglify())
        .pipe(sourcemaps.init({ loadMaps: false }))
        .pipe(concat('app.min.js'))
        .pipe(sourcemaps.write('../maps'))
        .pipe(dest(destination + '/js/'));
}
function img() {
    return src(source + '/assets/img/*.{png,jpg,jpeg,gif,svg}')
        .pipe(imagemin())
        .pipe(dest(destination + '/img/'));
}


exports.build = build;
exports.default = build;

exports.css = css;
exports.default = css;

exports.js = js;
exports.default = js;

exports.img = img;
exports.default = img;

exports.vendor = series(clean, modules);
exports.default = series;

//exports.watch.description = "Recompile à la volée tous les fichiers";
exports.build.description = exports.default.description = "Compile tous les fichiers (CSS, JS, images)";
exports.vendor.description = "Exporte les sources";
exports.css.description = "Compile en css les fichiers scss";
exports.js.description = "Compile les fichiers JavaScript";
exports.img.description = "Réduit le poids des images";


task('watch', function () {
    watch(source + '/assets/scss/style.scss', {
        events: 'all', ignoreInitial: false
    }, function (cb) {
        css();
        cb();
    });
    watch(source + '/assets/js/*.js', function (cb) {
        js();
        cb();
    });
    watch(source + '/assets/img/*', function (cb) {
        img();
        cb();
    });
});

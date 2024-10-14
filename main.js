// 'use strict';

// /*
// JavaScriptにおける数値表現
// */

// console.log(100);
// console.log(-1);
// console.log(1.5);


// 'use strict';

// console.log(10 + 3); // 13
// console.log(10 - 3); // 7
// console.log(10 * 3); // 30
// console.log(10 ** 3); // 1000
// console.log(10 / 3); // 3.3333....
// console.log(10 % 3); // 1

// console.log(10 + 2 * 3); // 16
// console.log((10 + 2) * 3); // 36

// 'use strict';

// // 定数を使う場合には、きちんと宣言をすること。
// // const price = 150;
// const rate = 1.1;
// let price = 150;

// console.log(price * 120 * rate);
// console.log(price * 130 * rate);
// console.log(price * 140 * rate);

// // 上書き（再代入）する場合は、constで宣言はせず、letにする。
// price = 100;
// console.log(price * 140 * rate);

// // price = price +1;
// // 元のpriceに自分自身を再代入することができる。
// price += 1;

// // 文字列の表現
// console.log('ハローワールド');
// // Windowsだったら「Alt」キーを押しながら、「￥」キーを押してください。macOSの場合は「Option」キーを押しながら「￥」キーを押してください。
// console.log('ハロー\'ワールド');

// const firstname = 'Taro';
// const lastname = 'Yamada';

// console.log('My name is' + firstname + lastname + 'です');
// // テンプレートリテラルを使う。 そのかわり、バッくクオートを入れること！
// console.log(`My name is ${firstname} ${lastname} です`);

// 'use strict';

// // My name is Taro Yamada, call me Taro!

// const fname = 'Taro';
// const lname = 'Yamada';

// // console.log('My name is ' + fname + ' ' + lname + ', call me ' + fname + '!');
// console.log(`My name is ${fname} ${lname}, call me ${fname}!`);

// const n = prompt('Any number?');

// console.log(n + 10);
// console.log(Number(n) + 10);

// const score = Number(prompt('Score?'));

// if (score >= 90) {
//     console.log('A');

// } else if (score >= 70) {
//     console.log('B');
// } else {
//     console.log('C');
// }

const color = Number(prompt('Color?'));

switch (color) {
    case 'red':
        console.log('Stop!');
        break;
    case 'yellow':
        console.log('Slow down!');
        break;
    case 'blue':
    case 'green':
        console.log('Go!');
        break;
    default:
        console.log('Wrong color');
        break;
}
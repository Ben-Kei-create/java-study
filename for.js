// for (let i = 0; i < 3; i++) {
//     console.log('Hello');
//     console.log(`${i}: Hello`);
//     for (let i = 1; i < 3; i++) {
//         console.log('こんにちは'); // Corrected line
//     } // Added closing brace for the inner loop
// }

// let command = Number(prompt('Menu 1, 2, 3 or 0 to exit')); // Changed from const to let

// while (command != 0) {
//     console.log(`Menu ${command} processed`);
//     command = Number(prompt('Menu 1, 2, 3 or 0 to exit'));
// }

// const score = Number(prompt('Score？'));
// const result = score > 80 ? 'A' : 'B';
// console.log(result);

'use strict';

const english = Number(prompt('English?'));
const math = Number(prompt('Math?'));

if (!(english >= 80 && math >= 80)) {
    console.log('A');
} else {
    console.log('B');
}
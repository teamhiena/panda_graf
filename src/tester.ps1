function test([string]$testfile, [string]$expectedfile) {
    $expResult = cat $expectedfile

    $input = cat $testfile
    $output = echo $input | java -cp .\class\ Main

    $diff = compare $output $expResult

    if ($diff.Length -eq 0) {
        echo "Test with test file $testfile passed."
    } else {
        echo "Test with test file $testfile FAILED!"
        echo "Got:"
        echo "-------------------------------------"
        echo $output
        echo "Expected:"
        echo "-------------------------------------"
        echo $expResult
    }
}

function testAll() {
    $tfiles = dir -fi "*.in" -Name

    foreach ($item in $tfiles) {
        $temp = $item.Substring(0, $item.LastIndexOf('.'))

        test $item "$temp.out"
    }
}

#main

if ($args.Count -eq 0) {
    testAll
} elseif($args.Count -eq 2) {
    test $args[0] $args[1]
} else {
    echo "Wrong number of arguments!"
}


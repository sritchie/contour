#!/bin/sh
#
# Bootstrapping script for ClojureScript!
# Run with "sh bootstrap.sh /path/to/root/cljs/dir"
# 
# For example:
#
# $ sh bootstrap.sh ~/code/cljs

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "1 argument required, $# provided"

root_dir=`echo "$1" | sed 's#/*$##'`
cljs_root=$root_dir/clojurescript
cljs_bin=$cljs_root/bin

echo "Attempting to switch to $root_dir..."
cd $root_dir &>/dev/null || die "Root directory \"$root_dir\" doesn't exist. Please try again."
if [ ! -d $cljs_root ]; then
    git clone git://github.com/clojure/clojurescript.git
fi

cd clojurescript
./script/bootstrap

echo "
Add the following lines to your .bash_profile:

export CLOJURESCRIPT_HOME=$cljs_root
export PATH=$cljs_root/bin:\$PATH

Running the following command will do the trick:

echo \"export CLOJURESCRIPT_HOME=$cljs_root\\nexport PATH=$cljs_bin:\$PATH\" > ~/.bash_profile
"
